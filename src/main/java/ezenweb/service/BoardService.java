package ezenweb.service;


import ezenweb.model.dto.BoardDto;
import ezenweb.model.dto.MemberDto;
import ezenweb.model.dto.PageDto;
import ezenweb.model.entity.BoardEntity;
import ezenweb.model.entity.MemberEntity;
import ezenweb.model.repository.BoardEntityRepository;
import ezenweb.model.repository.MemberEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.io.File;
import java.util.*;

@Service
public class BoardService {

    @Autowired
    private BoardEntityRepository boardEntityRepository;
    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberEntityRepository memberEntityRepository;
    @Autowired
    private FileService fileService;

    // 1. [C] 게시글 등록
    @Transactional
    public boolean postBoard( BoardDto boardDto ) {

        /*
            MemberEntity                                BoardEntity
             [p]  mno                                       [p] bno
                                <--- 단방향 --               [f] mno    @ManyToOne  MemberEntity

                                ---- 양방향 -->
             @OneToMany(mappedBy = "memberEntity")
             []List<BoardEntity> boardEntityList

         */
        // 1. FK 키의 엔티티를 찾는다.
        // [ FK로 사용할 PK를 알고 있어야 있어야한다. 세션,매개변수 가져오기 ]
        // ================================= 단방향 ================================================= //
            // 1. 예 ) 로그인된 회원의 pk번호 호출
             MemberDto loginMemberDto = memberService.getMember();
             if( loginMemberDto == null ){
                 return false;
             }
            // 2. 회원pk번호를 가지고 pk엔티티 찾기
        Optional<MemberEntity> memberEntityOptional
                = memberEntityRepository.findById( loginMemberDto.getMno() );
            // 3. 유효성검사 [ 로그인이 안된상태 글쓰기 실패 ]
        if( !memberEntityOptional.isPresent() ){ return false;}

            // 4. 단방향 저장  [ 게시물 엔티티에 회원엔티티 넣어주기 ]
                // 1. 게시물 생성 [ fk에 해당하는 레코드 생성 ]
        BoardEntity boardEntity  = boardEntityRepository.save( boardDto.saveToBoardEntity() );
                // 2. 생성된 게시물에 작성자엔티티 넣어주기 [ fk 넣어주기 ]
        boardEntity.setMemberEntity( memberEntityOptional.get() );
        // ================================= 단방향 end ================================================= //

        // 받아온 DTO를 엔터티로 바꾸고 저장하기
        //BoardEntity boardEntity = boardEntityRepository.save(boardDto.saveToBoardEntity());

        // ================================= 양방향 ================================================= //
            // 5. 양방향 저장 [ 회원엔티티에 게시물 엔티티 넣어주기 ]
        memberEntityOptional.get().getBoardEntitiyList().add( boardEntity );
        // ================================= 양방향 end ================================================= //

        // 응답하기 ( bno가 1보다 큰지 조건 거는 이유 : 0 이상이어야 auto-increment 적용 된거라서.
        if( boardEntity.getBno() >= 1) {
            // 게시물쓰기 성공시에 파일 처리 하기
            String filename = fileService.fileUpload( boardDto.getFile() );
            if (filename != null) { boardEntity.setBfile( filename ); }
            return true;
        } return false;


    }

    // 2. [R] 게시글 출력 (전체)
    @Transactional
    public PageDto getBoard( int page, String key, String keyword, int view) {

        // JPA 페이징 처리 라이브러리 지원
            // 1. Pageable : 페이지 인터페이스
                // 사용 이유 : Repository인터페이스가 페이징 처리할때 사용하는 인터페이스
            // 2. 인터페이스는 무조건 구현체가 필요하다. ( 추상메소드를 구현해 주는 객체 )
                // of(현재페이지)
                // 현재페이지는 0 부터 시작함. 그래서 1깍아주기
                // size 페이지별 게시물수
            // 3. Page는 List와 마찬가지로 여러개의 객체를 저장하는 타입이다 .
                // 하지만 다르게 추가적으로 getTotalPages를 줌 ㄷㄷㄷㄷㄷㄷㄷ map도 쓸수있음 킹왕짱편리함이다.

                            // new 안하는 이유 : 함수가 휘어있음.. 스태틱임. 그럼 객체 만들어 쓸 필요가 없음.
        Pageable pageable  = PageRequest.of(page-1,view);
                        // + 정렬도 지원해줌 PageRequest.of(page-1,view, Sort.by( Sort.Direction.DESC또는ASC, "기준필드명"));


        // 1. 모든 게시물 출력이니 일단 죄다 꺼내오기
        // Page<BoardEntity> boardEntities = boardEntityRepository.findAll(pageable);
        Page<BoardEntity> boardEntities =
                boardEntityRepository.findBySearch( key, keyword, pageable);

        // 2. 받아온 Entity를 Dto 로 변환하기
        List<BoardDto> resultList = new ArrayList<>();
        boardEntities.forEach( list -> {
            resultList.add( list.saveToBoardDto() );
        });

            // 3. 총 페이지수
        int totalPages = boardEntities.getTotalPages();
            // 4. 총 게시물수
        long totalCount = boardEntities.getTotalElements();


        // 5. DTO 구성해서 반환하기
        PageDto resuldto = PageDto.builder()
                .boardDtos( resultList )
                .totalPages( totalPages )
                .totalCount( totalCount )
                .build();


        return resuldto;
    }

    // 2-2. [R] 게시글 출력 (개별)
    @Transactional
    public BoardDto getBoardOne( int bno ) {
        Optional<BoardEntity> boardEntity = boardEntityRepository.findById(bno);
        if( boardEntity.isPresent() ) {
            BoardEntity entity = boardEntity.get();
            // 조회수 증가
            entity.setBview( entity.getBview()+1 );
            return entity.printOneToBoardDto();
        }
        return null;
    };

    // 3. [U] 게시글 수정
    @Transactional
    public boolean putBoard( BoardDto boardDto ) {
        System.out.println("boardDto = " + boardDto);

        // bno를 이용해서 수정해야 할 게시물 찾기
        Optional<BoardEntity> entityOptional = boardEntityRepository.findById(boardDto.getBno());

        // 만약에 값이 검색 되었다면
        if( entityOptional.isPresent() ) {
            // 꺼내기
            BoardEntity boardEntity = entityOptional.get();
            // 수정하기
            boardEntity.setBtitle(boardDto.getBtitle());
            boardEntity.setBcontent(boardDto.getBcontent());
            boardEntity.setBfile(boardDto.getBfile());
            return true;
        }
        return false;

    }

    // 4. [D] 게시글 삭제
    @Transactional
    public boolean deleteBoard( int bno ) {
        System.out.println("bno = " + bno);

        if( boardEntityRepository.existsById(bno) ) {
            boardEntityRepository.deleteById(bno);
            return true;
        } else { return false; }

    }


}
