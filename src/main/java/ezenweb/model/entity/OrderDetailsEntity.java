package ezenweb.model.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "orders_details")
@AllArgsConstructor @NoArgsConstructor @Getter @Setter @ToString @Builder
public class OrderDetailsEntity {

    // 주문상세번호
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int odtno;

    // 주문번호
    @Column(name = "ono", nullable = false)
    private int ono;

    // 상품번호
    @Column(name = "pno", nullable = false)
    private int pno;

}
