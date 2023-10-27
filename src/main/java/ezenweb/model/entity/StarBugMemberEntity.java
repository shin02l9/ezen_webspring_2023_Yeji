package ezenweb.model.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "members")
@AllArgsConstructor @NoArgsConstructor @Getter @Setter @ToString @Builder
public class StarBugMemberEntity {

    // 회원번호
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int mno;

    // 회원이름
    @Column(name = "mname", nullable = false)
    private String mname;

    // 회원전화번호
    @Column(name = "mphone", nullable = false)
    private String mphone;

    // 회원나이
    @Column(name = "mage", nullable = false)
    private String mage;

    // 회원성별
    @Column(name = "msex", nullable = false)
    private String msex;

    // 회원가입 날짜
    @Column(name = "mdate", nullable = false)
    private String mdate;

    // 회원추가정보 (메모)
    @Column(name = "metc", nullable = false)
    private String metc;

}
