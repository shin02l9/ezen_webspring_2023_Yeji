package day01.webMvc;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor  // 빈 생성자 자동 생성
@AllArgsConstructor // 풀 생성자 자동 생성
@Getter @Setter     // Getter Setter 자동 생성
@ToString           // ToString 자동 생성
@Builder            // 빌더 패턴 사용
public class WebDto { // todo list 클래스

    private int tno;              // todo list 번호
    private String title;         // todo list 내용
    private LocalDate date;       // todo list 작성일
    private boolean finished;     // todo list 실행여부


}
