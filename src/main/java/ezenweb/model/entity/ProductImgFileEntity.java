package ezenweb.model.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "productImg")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class ProductImgFileEntity { // 파일 테이블

    @Id
    private String uuidFlieName;    // 이미지 식별번호 [PK]
    @Column
    private String realFlieName;    // 이미지 실제이름

    // 제품번호 [FK] ★단방향
    @ToString.Exclude
    @JoinColumn(name = "pno")
    @ManyToOne
    private ProductEntity productEntity;
}
