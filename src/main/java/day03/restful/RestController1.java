package day03.restful;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller // 해당 클래스를 스프링MVC 중 컨트롤러 객체로 사용 // 스프링 컨트롤러 객체를 빈에 등록
public class RestController1 {
    // 1. get
    @RequestMapping( value = "/day03/black", method = RequestMethod.GET)
    protected void getBlack(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 요청
        String param1 = req.getParameter("param1");
        System.out.println("param1 = " + param1);
        // 2. 응답
        resp.setContentType("text/html;charset=UTF-8");
        resp.getWriter().println("정상응답");
    }

    // 2. post
    @RequestMapping( value = "/day03/black", method = RequestMethod.POST)
    protected void postBlack(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 요청
        String param1 = req.getParameter("param1");
        System.out.println("param1 = " + param1);
        // 2. 응답
        resp.setContentType("text/html;charset=UTF-8");
        resp.getWriter().println("정상응답");
    }

    // 3. put
    @RequestMapping( value = "/day03/black", method = RequestMethod.PUT)
    protected void putBlack(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 요청
        String param1 = req.getParameter("param1");
        System.out.println("param1 = " + param1);
        // 2. 응답
        resp.setContentType("text/html;charset=UTF-8");
        resp.getWriter().println("정상응답");
    }

    // 4. delete
    @RequestMapping( value = "/day03/black", method = RequestMethod.DELETE)
    protected void deleteBlack(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 요청
        String param1 = req.getParameter("param1");
        System.out.println("param1 = " + param1);
        // 2. 응답
        resp.setContentType("text/html;charset=UTF-8");
        resp.getWriter().println("정상응답");
    }

}
