package ezenweb.model.dto;

import ezenweb.model.entity.BoardEntity;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter @ToString
@Builder
public class BoardDto {

    private int bno;
    private String btitle;
    private String bcontent;
    private int bview;
    private String bfile;

    private int mno;
    private String mname;

    public String cdate;
    public String udate;

    // 첨부파일 [ 스프링에서 지원하는 첨부파일 라이브러리 ]
    private MultipartFile file;



    // Dto -> Entity
    // 저장할 때
    public BoardEntity saveToBoardEntity() {
      return BoardEntity.builder()
              .btitle( this.btitle )
              .bcontent( this.bcontent )
              .bfile( this.bfile)
              .build();
    }

}
