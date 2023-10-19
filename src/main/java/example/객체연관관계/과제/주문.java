package example.객체연관관계.과제;

import lombok.*;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter @ToString @Builder
public class 주문 {
    private int 주문번호;
    private int 주문수량;
    @ToString.Exclude
    private 제품 제품번호;
}
