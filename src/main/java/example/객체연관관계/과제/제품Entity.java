package example.객체연관관계.과제;


import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product")
@AllArgsConstructor @NoArgsConstructor @Getter @Setter @ToString @Builder
public class 제품Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pno;
    @Column(name = "pname")
    private String pname;


    @ToString.Exclude
    @JoinColumn(name = "cno_fk") //FK 키로 사용
    @ManyToOne
    private 카테고리Entity cno;

    // 양방향을 위해 추가 (PK)
    @Builder.Default // 빌더 패턴 사용시 해당 필드 값을 기본값으로 사용.
    @OneToMany( mappedBy = "pno")
    private List<주문Entity> orderList = new ArrayList<>();


}
