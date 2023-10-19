package example.객체연관관계;

import lombok.*;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter  @Builder @ToString
public class 게시물 {// (PK)
    private int 게시물번호;
    private String 게시물제목;
    private String 게시물내용;

    @ToString.Exclude
    private 회원 작성한회원; // 회원객체의 주소값 가지는 필드 (FK) : 단방향이다.

}
