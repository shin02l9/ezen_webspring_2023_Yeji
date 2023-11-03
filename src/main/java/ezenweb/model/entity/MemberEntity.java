package ezenweb.model.entity;


import ezenweb.model.dto.MemberDto;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity // 해당클래스를 db테이블과 매핑
@Table(name = "member") // 테이블면 정의 가능 생략시는 해당 클래스명이 곧 db테이블명으로 자동 생성된다.
@DynamicInsert
@AllArgsConstructor @NoArgsConstructor @Getter @Setter @ToString @Builder
public class MemberEntity extends BaseTime{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto-increment 로 사용한단 뜻
    private int mno;            // 1. 회원번호

    // 해당 필드 선정 | name="필드명" | length = 글자수 |nullable = false -> Not null 이라는 뜻 | unique = true -> 중복제거
    @Column(name = "memail", length = 50, nullable = false, unique = true)
    private String memail;      // 2. 이메일

    @Column(name = "mpassword", length = 100, nullable = false)
    private String mpassword;   // 3. 비밀번호

    @Column(name = "mname", length = 20, nullable = false)
    private String mname;       // 4. 이름

    @Column(name = "mphone", length = 13, nullable = true, unique = true)
    private String mphone;      // 5. 전화번호

    @Column(name = "mrole", length = 10 )
    @ColumnDefault("'ROLE_USER'") // 초기값 설정하기 @ColumnDefault("초기값") -> 문자이면 작은따옴표도 넣어야함.
    private String mrole;       // 6. 회원등급 ( 일반회원과 관리자회원 구분하기 )

    private String mpasswordnew;

    // 양방향을 위해 추가 (PK)
    @Builder.Default // 빌더 패턴 사용시 해당 필드 값을 기본값으로 사용.
    @OneToMany( mappedBy = "memberEntity")
    private List<BoardEntity> boardEntitiyList = new ArrayList<>();


    // entity -> dto 함수 필요
    public MemberDto toMemberDto() {
        return  MemberDto.builder()
                .mno( this.mno )
                .memail( this.memail )
                .mpassword( this.mpassword )
                .mname( this.mname )
                .mphone( this.mphone )
                .mrole( this.mrole )
                .cdate( this.getCdate() )
                .udate( this.getUdate() )
                .build();
    }
}
