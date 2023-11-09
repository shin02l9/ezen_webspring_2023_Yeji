package ezenweb.model.entity;

import lombok.Builder;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product")
public class ProductEntity { // 제품 테이블

    @Id
    private String pno;             // 제품번호 [PK]
    @Column
    private String pname;           // 제품명
    @Column( columnDefinition = "TEXT" )
    private String pcommnet;        // 제품설명
    @Column
    private int pprice;             // 제품가격
    @Column @ColumnDefault("0")
    private byte pstate;            // 제품상태 [ 0: 판매중, 1: 판매중지, 2: 재고없음, 3:폐기 ]
    @Column @ColumnDefault("0")
    private int pstock;             // 제품재고


    // 제품카테고리 번호 [FK] ★단방향
    @ToString.Exclude
    @JoinColumn(name = "pcno")
    @ManyToOne
    private ProductCategoryEntity productCategory;

    // 제품이미지 [FK] ★양방향★
    @Builder.Default
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "productEntity", cascade = CascadeType.ALL) // mappedBy 쓰는 이유 DB가 또 테이블을 생성하면 안되니까
    private List<ProductImgFileEntity> imgFileEntities = new ArrayList<>();

}
