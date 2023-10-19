package example.객체연관관계;

import lombok.*;


@AllArgsConstructor @NoArgsConstructor
@Getter @Setter @ToString @Builder
public class 댓글 {
    private int 댓글번호;
    private String 댓글내용;

    @ToString.Exclude
    private 게시물 댓글의게시물; // 단방향 (FK)
}
