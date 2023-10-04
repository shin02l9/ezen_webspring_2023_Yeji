package day02.servlet;

import lombok.*;

import java.time.LocalDate;

// 룸북
@Getter@Setter      // 각 필드별 Getter/Setter
@ToString           // 객체의 필드 정보를 출력하는 메소드 자동생성
@NoArgsConstructor  // 빈 생성자
@AllArgsConstructor // 풀 생성자
@Builder            // 객체 생성시 사용 할수 있는 함수
public class TodoDto {

    private Long tno;
    private String title;
    private LocalDate dueDate;
    private boolean finished;
}
