package ezenweb.model.entity;


import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "coupon_history")
@AllArgsConstructor @NoArgsConstructor @Getter @Setter @ToString @Builder
public class CouponHistoryEntity {

    // 쿠폰관련 로그 순번
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int chisno;

    // 쿠폰코드
    @Column(name = "chcode", nullable = false)
    private String chcode;

    // 쿠폰상태
    @Column(name = "chstate", nullable = false)
    private int chstate;

    // 생성(발급)날짜
    @Column(name = "chdatec", nullable = false)
    private String chdatec;

    // 사용한(상태변경한)날짜
    @Column(name = "chdateu", nullable = false)
    private String chdateu;

    // 쿠폰 할인율 식별번호
    @Column(name = "ccno", nullable = false)
    private int ccno;

    // 쿠폰 소유한 회원번호
    @Column(name = "mno", nullable = false)
    private int mno;
}
