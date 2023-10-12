package example.day03.restful;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller // 해당 클래스를 스프링MVC 중 컨트롤러 객체로 사용 // 스프링 컨트롤러 객체를 빈에 등록
public class RestController2 {
    // 1. get
    @RequestMapping( value = "/day03/orange", method = RequestMethod.GET)
    @ResponseBody
    protected String getOrange(HttpServletRequest req) throws ServletException, IOException {
        // 1. 요청
        String param1 = req.getParameter("param1");
        System.out.println("param1 = " + param1);
        // 2. 응답
        return "정상응답";
    }

    // 2. post
    @RequestMapping( value = "/day03/orange", method = RequestMethod.POST)
    @ResponseBody
    protected String postOrange(HttpServletRequest req) throws ServletException, IOException {
        // 1. 요청
        String param1 = req.getParameter("param1");
        System.out.println("param1 = " + param1);
        // 2. 응답
        return "정상응답";
    }

    // 3. put
    @RequestMapping( value = "/day03/orange", method = RequestMethod.PUT)
    @ResponseBody
    protected String putOrange(HttpServletRequest req) throws ServletException, IOException {
        // 1. 요청
        String param1 = req.getParameter("param1");
        System.out.println("param1 = " + param1);
        // 2. 응답
        return "정상응답";
    }

    // 4. delete
    @RequestMapping( value = "/day03/orange", method = RequestMethod.DELETE)
    @ResponseBody
    protected String deleteOrange(HttpServletRequest req) throws ServletException, IOException {
        // 1. 요청
        String param1 = req.getParameter("param1");
        System.out.println("param1 = " + param1);
        // 2. 응답
        return "정상응답";
    }

}
