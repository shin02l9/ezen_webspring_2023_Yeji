package example.객체연관관계.과제;


import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "category")
@AllArgsConstructor @NoArgsConstructor @Getter @Setter @ToString @Builder
public class 카테고리Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cno;

    @Column(name = "cname")
    private String cname;

    // 양방향을 위해 추가 (PK)
    @Builder.Default // 빌더 패턴 사용시 해당 필드 값을 기본값으로 사용.
    @OneToMany( mappedBy = "pno")
    private List<제품Entity> productList = new ArrayList<>();


}
