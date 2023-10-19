package example.객체연관관계.과제;

import lombok.*;


import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter @ToString @Builder
public class 카테고리 {
    private int 카테고리번호;
    private String 카테고리명;
    @Builder.Default
    private List<제품> 제품리스트 = new ArrayList<>();


}
