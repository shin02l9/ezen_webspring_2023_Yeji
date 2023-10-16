package ezenweb.model.dto;


import ezenweb.model.entity.MemberEntity;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter @ToString @Builder
public class MemberDto {

    private int mno;            // 1. 회원번호
    private String memail;      // 2. 이메일
    private String mpassword;   // 3. 비밀번호
    private String mname;       // 4. 이름
    private String mphone;      // 5. 전화번호
    private String mrole;       // 6. 회원등급 ( 일반회원과 관리자회원 구분하기 )

    private LocalDateTime cdate;
    private LocalDateTime udate;

    // dto -> entity 함수 필요
    public MemberEntity toMemberEntity() {
        return MemberEntity.builder()
                .mno( this.mno )
                .memail( this.memail )
                .mpassword( this.mpassword )
                .mname( this.mname )
                .mphone( this.mphone )
                .mrole( this.mrole )
                .build();
    }
}
