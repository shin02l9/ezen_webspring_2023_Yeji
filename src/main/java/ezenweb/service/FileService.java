package ezenweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.util.UUID;

@Service
public class FileService { // 파일 관련 메소드들을 정의

    // 0. 파일 경로 설정하기 ( 배포전 )
    // private String fileRootPath = "C:\\java\\";
    private String fileRootPath = "C:\\Users\\504\\Desktop\\ezen_webspring_2023_Yeji\\build\\resources\\main\\static\\static\\media\\";
    // 1. 업로드
    public String fileUpload (MultipartFile multipartFile){
        // 0. 유효성 검사
        if( multipartFile.isEmpty()){
            return null;
        }
        // 1. 파일명 ( 파일명만으로는 식별이 어렵다, 동일한 파일명에 다른 내용물이 있을 수 있으니까 )
            // 해결방법 : 상위컨텐츠 PK를 넣거나 난수로 뒤에 식별번호를 추가하거나 날짜 시간등을 추가하거나
        String filename =
                UUID.randomUUID().toString()+"_"+ // UUID를 이용한 파일 식별자 만들기
                multipartFile.getOriginalFilename()
                        .replaceAll("_", "-"); // '_' -> '-' 변경
        // 2. 파일 경로
        File file = new File( fileRootPath + filename);
        // 3. 업로드
        try{
            multipartFile.transferTo(file);
            return filename;
        } catch (Exception e){
            System.out.println( "파일 업로드 실패 : "+e);
            return null;
        }
    }

    // 2. 다운로드
    @Autowired
    private HttpServletResponse response;

    public void fileDownload( String UUIDfilename ){
        // 1. 다운로드할 파일명 찾기
        String downloadFilePath = fileRootPath + UUIDfilename;
        // 2. UUID가 제거된 순수 파일명 [ 다운로드시 출력되는 파일명 이니까 UUID 제거 ]
        String fileName = UUIDfilename.split("_")[1]; // _ 기준으로 쪼갠 후 뒷자리 파일명만 호출
        // 3. 다운로드 형식 구성 -> 브라우저가 지원하는 다운로드 형식이라 커스텀이 불가능 하다
        try {   // 이 코드를 작성해야 브라우저에서 다운로드 되는 화면? 팝업이 뜸!!!!!!!!!!!!!!!!!!!!!!
            response.setHeader("Content-DisPosition",
                    "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));

        // 4. 다운로드 실행
            // ------------------------ 서버가 해당 파일 읽어오기  ----------------------------------
            // 1. 서버가 해당 파일 읽어오기
            File file = new File(downloadFilePath);
            // 2. 버퍼스트링 이요한 바이트로 파일 읽어오기
            BufferedInputStream fin = new BufferedInputStream( new FileInputStream(file));
            // 3. 파일의 용량 만큼 읽어온 바이트배열 선언해두기
            byte[] bytes = new byte[ (int)file.length() ];
            // 4. 버퍼스트링이 읽어온 바이트들을 바이트 배열에 저장
            fin.read( bytes );

            // ------------------------ 서버가 읽어온 파일을 클라이언트에게 응답하기 ---------------------
            // 5. 버퍼스트림 이용한 response 으로 응답하기
            BufferedOutputStream fout = new BufferedOutputStream( response.getOutputStream() );
            // 6. 읽어온 바이트[파일] 내보내기
            fout.write( bytes );
            // 7. 안전하게 스트림 닫아주기
            fout.flush(); fout.close(); fin.close();

        } catch ( Exception e){
            System.out.println( "파일 다운로드 실패 : "+e);
        }

    }

}

//        // 파일처리 라이브러리가 제공하는 함수 사용해보기
//        System.out.println("boardDto = " + boardDto);
//        System.out.println("첨부파일 객체 boardDto.getFile = " + boardDto.getFile());
//        System.out.println("첨부파일 객체 필드명 boardDto.getFile.getName = " + boardDto.getFile().getName());
//        System.out.println("첨부파일 객체 파일명 boardDto.getFile.getOriginalFilename = " + boardDto.getFile().getOriginalFilename()); // 파일명
//        System.out.println("첨부파일 객체 용량 boardDto.getFile.getSize = " + boardDto.getFile().getSize());  // 용량
//        System.out.println("첨부파일 객체 확장자 boardDto.getFile.getContentType = " + boardDto.getFile().getContentType()); // 확장자
//
//        // 파일 클래스를 이용해서 경로 설정하고 파일 저장하기
//        File file = new File("C:\\java\\"+boardDto.getFile().getOriginalFilename());
//        try{
//            boardDto.getFile().transferTo(file);
//            System.out.println("파일 업로드 성공");
//        } catch ( Exception e ){
//            System.out.println("e : "+ e);
//            System.out.println("파일 업로드 실패");
//        }