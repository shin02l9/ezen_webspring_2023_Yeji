package example.day06;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class NoteEntity {
    @Id // no 필드를 pk로 선정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // pk 선언하는 방법을 정의하는 것. // auto_increment
    private int no; // 게시물 번호
    private String title; // 게시물 내용
    private String writer; // 작성자
    private String password; // 비밀번호
    private LocalDateTime date; // 작성일



}
