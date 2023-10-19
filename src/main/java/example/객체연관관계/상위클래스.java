package example.객체연관관계;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter @ToString @Builder
public class 상위클래스 {
    private String data;
    @Builder.Default
    private List<하위클래스> 참조중인하위객체들 = new ArrayList<>();
}
