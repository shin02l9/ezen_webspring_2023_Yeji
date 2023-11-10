package ezenweb.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "productCategory")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class ProductCategoryEntity { // 제품 카테고리 테이블

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pcno;       // 제품카테고리번호 [PK]
    @Column
    private String pcname;  // 제품카테고리이름

    // 제품들 [FK] ★양방향★
    @Builder.Default
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "productCategory", cascade = CascadeType.ALL) // mappedBy 쓰는 이유 DB가 또 테이블을 생성하면 안되니까
    private List<ProductEntity> productEntities = new ArrayList<>();







    /*
        fetch   : 양방향일때 참조를 불러오는 로딩에 대한 옵션을 설정가능
            fetch = FetchType.LAZY            : 참조를 사용할 때 호출 (지연로딩) -> JAVA에서 .get~ 할때
            fetch = FetchType.EAGER [기본값]   : 참조값을 즉시 호출 (즉시로딩) -> DB에서 select 할때



        cascade : 영속성 제약조건
            -> DB에 대한것이 아니라 Entity에 대한것임 !
               결론적으로 Entity가 DB와 연결되어있어서 영향이 가긴 하지만 직접적인것이 아님 !!!!!)
            cascade = CascadeType.ALL       : ★ REMOVE와 PERSIST를 둘 다 적용
            cascade = CascadeType.REMOVE    : ★ 부모가 삭제 될때 자식도 같이 삭제 [ 부모와 자식을 모두 제거 ]
            cascade = CascadeType.PERSIST   : ★ 부모를 호출 할때 자식도 같이 호출 [ 부모와 자식을 한번에 영속화 ]
            cascade = CascadeType.REFRESH   : 부모의 엔티티가 업데이트 되면 자식객체의 값도 새로고침
            cascade = CascadeType.MERGE     : 부모의 엔티티가 수정될 때 자식 객체도 조회 후 업데이트 (병합)
            cascade = CascadeType.DETACH    : 영속성 제거



    */
}
