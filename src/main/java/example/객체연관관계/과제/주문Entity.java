package example.객체연관관계.과제;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "orders")
@AllArgsConstructor @NoArgsConstructor @Getter @Setter @ToString @Builder
public class 주문Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ono;
    @Column(name = "ocount")
    private int ocount;


    @ToString.Exclude
    @JoinColumn(name = "pno_fk") //FK 키로 사용
    @ManyToOne
    private 제품Entity pno;

}
