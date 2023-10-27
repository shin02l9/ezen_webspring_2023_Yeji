package ezenweb.model.entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Table(name = "orders")
@AllArgsConstructor @NoArgsConstructor @Getter @Setter @ToString @Builder
public class OrdersEntity {

    // 주문번호
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ono;

    // 회원번호
    @Column(name = "mno", nullable = false)
    private int mno;

    // 주문날짜
    @Column(name = "odate", nullable = false)
    private String odate;

    // 주문상태 | 1:결제, 2:환불
    @Column(name = "ostate", nullable = false)
    @ColumnDefault("1")
    private int ostate;

    // 총 주문금액
    @Column(name = "totalprice", nullable = false)
    private int totalprice;

    // 총 결제금액
    @Column(name = "paid", nullable = false)
    private int paid;


}
