package example.객체연관관계.과제;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter @ToString @Builder
public class 제품 {
    private int 제품번호;
    private String 제품이름;
    @ToString.Exclude
    private 카테고리 카테고리번호;
    @Builder.Default
    private List<주문> 주문리스트 = new ArrayList<>();
}
