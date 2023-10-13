package example.day06;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


// Controller 사용처 | 웹 : JS(AJAX), React(AXIOS), 앱, 소프트웨어
// 역할 : AJAX [외부인] <------ 연결다리 [자바] ------> 서비스 [자바] ------Repository----Entity---> DB
// 예시 : 식당손님 (주문) <---대화or행위[★객체]--- 서버 (서빙) ---대화or행위[★객체]---> 요리사 (요리) ---대화or행위[★객체]---> 냉장고 (재료)
// 대화/행위 : 상호작용
@RestController
@RequestMapping("/note")
public class NoteController {

    @Autowired
    private NoteService noteService;

    // 1. [C]
    @PostMapping("/do")
    public boolean bWrite(@RequestBody NoteDto noteDto) {
        boolean result = noteService.bWrite(noteDto);
        return result;
    }
    // 2. [R]
    @GetMapping("/do")
    public List<NoteDto> bList() {
        List<NoteDto> list = noteService.bList();
        return list;
    }

    // 3. [U]
    @PutMapping("/do")
    public boolean bUpdate(@RequestBody NoteDto noteDto) {
        boolean result = noteService.bUpdate(noteDto);
        return result;
    }
    // 4. [D]
    @DeleteMapping("/do")
    public boolean bDelete(@RequestParam int no) {
        boolean result = noteService.bDelete(no);
        return result;
    }

}
