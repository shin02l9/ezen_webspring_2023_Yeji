package day01.webMvc;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController // 해당 클래스는 restful로 사용하겠다는 선언 (서블릿)
public class WebController {

    @GetMapping("/day01/doget") // doGet을 쓰겠단 소리!!!!! HTTP가 get 요청을 했을때.
    // 그런데 주소 직접 만들어야함 , JSP는 알아서 해주는데.. 이게 단점
    public List<WebDto> doGet(){
        WebDao dao = new WebDao();
        List<WebDto> result = dao.doGet();


        return result;
    } // doGet e

    @PostMapping("/day01/dopost") // doPost를 쓰겠단 소리!!!!! HTTP가 post 요청을 했을때.
    public boolean doPost(String title){

        // 1. 인수 받아서 Dto 생성
        WebDto dto = new WebDto( 0, title, LocalDate.now(), true);
        // 2. Dao 에게 전달
        WebDao dao = new WebDao();
        boolean result = dao.doPost( dto );

        return result;
    } // doPost e

}
