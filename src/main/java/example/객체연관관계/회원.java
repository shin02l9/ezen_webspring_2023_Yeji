package example.객체연관관계;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor@NoArgsConstructor
@Getter@Setter@Builder @ToString
public class 회원 {// (PK)
    private int 회원번호;
    private String 회원아이디;
    private String 회원이름;
    @Builder.Default // 빌더패턴 사용시 해당 필드는 기본값으로 사용하겠다는 뜻
    private List<게시물> 내가쓴글 = new ArrayList<>();
}
