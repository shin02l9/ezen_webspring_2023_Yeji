package example.day03.restful;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller // 해당 클래스를 스프링MVC 중 컨트롤러 객체로 사용 // 스프링 컨트롤러 객체를 빈에 등록
public class RestController4 {
    // 1. get
    @GetMapping("/day03/blue")
    protected String getBlue(HttpServletRequest req) throws ServletException, IOException {
        // 1. 요청
        String param1 = req.getParameter("param1");
        System.out.println("param1 = " + param1);
        // 2. 응답
        return "정상응답";
    }

    // 2. post
    @PostMapping("/day03/blue")
    protected String postBlue(HttpServletRequest req) throws ServletException, IOException {
        // 1. 요청
        String param1 = req.getParameter("param1");
        System.out.println("param1 = " + param1);
        // 2. 응답
        return "정상응답";
    }

    // 3. put
    @PutMapping("/day03/blue")
    protected String putBlue(HttpServletRequest req) throws ServletException, IOException {
        // 1. 요청
        String param1 = req.getParameter("param1");
        System.out.println("param1 = " + param1);
        // 2. 응답
        return "정상응답";
    }

    // 4. delete
    @DeleteMapping("/day03/blue")
    protected String deleteBlue(HttpServletRequest req) throws ServletException, IOException {
        // 1. 요청
        String param1 = req.getParameter("param1");
        System.out.println("param1 = " + param1);
        // 2. 응답
        return "정상응답";
    }

}
