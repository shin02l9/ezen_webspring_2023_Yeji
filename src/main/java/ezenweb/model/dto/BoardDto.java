package ezenweb.model.dto;

import ezenweb.model.entity.BoardEntity;
import lombok.*;

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

    public LocalDateTime cdate;
    public LocalDateTime udate;

    // Dto -> Entity
    // 저장할 때
    public BoardEntity saveToBoardEntity() {
      return BoardEntity.builder()
              .btitle( this.btitle )
              .bcontent( this.bcontent )
              .bfile( this.bfile)
              .mno( this.mno )
              .build();
    };

}
