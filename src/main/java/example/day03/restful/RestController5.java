package example.day03.restful;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController //  @Controller 동일한 기능 + @ResponseBody 제공
@RequestMapping( value = "/day03") // 클래스에 매핑URL 정의할 경우. 해당 클래스 안에 메소드들의 공통URL
public class RestController5 {
    // 1. GET
    @GetMapping("/pink")
    public String getPink(HttpServletRequest request ) throws IOException {
        String param1 = request.getParameter("param1");
        System.out.println("param1 = " + param1);
        return "정상응답";   // 2.응답
    }
    // 2. POST
    @PostMapping("/pink")
    public String postPink(HttpServletRequest request  ) throws IOException {
        String param1 = request.getParameter("param1");
        System.out.println("param1 = " + param1);
        return "정상응답";   // 2.응답
    }
    // 3. PUT
    @PutMapping("/pink")
    public String putPink(HttpServletRequest request ) throws IOException {
        String param1 = request.getParameter("param1");
        System.out.println("param1 = " + param1);
        return "정상응답";   // 2.응답
    }
    // 4. DELETE`
    @DeleteMapping("/pink")
    public String deletePink(HttpServletRequest request ) throws IOException {
        String param1 = request.getParameter("param1");
        System.out.println("param1 = " + param1);
        return "정상응답";   // 2.응답
    }
}