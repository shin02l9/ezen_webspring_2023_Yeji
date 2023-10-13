package example.day06;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@ToString
@Builder
public class NoteDto {

    private int no; // 게시물 번호
    private String title; // 게시물 내용
    private String writer; // 작성자
    private String password; // 비밀번호
    private LocalDateTime date; // 작성일



}
