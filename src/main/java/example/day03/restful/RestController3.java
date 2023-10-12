package example.day03.restful;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController //  @Controller 동일한 기능 + @ResponseBody 제공
public class RestController3 {
    // 1. GET
    // @ResponseBody : 해당 클래스가 @RestController 인 경우에는 생략 가능
    @RequestMapping( value = "/day03/red"  , method = RequestMethod.GET )
    public String getRed(HttpServletRequest request ) throws IOException {
        String param1 = request.getParameter("param1");
        System.out.println("param1 = " + param1);
        return "정상응답";   // 2.응답
    }
    // 2. POST
    @RequestMapping( value = "/day03/red"  , method = RequestMethod.POST )
    public String postRed(HttpServletRequest request  ) throws IOException {
        String param1 = request.getParameter("param1");
        System.out.println("param1 = " + param1);
        return "정상응답";   // 2.응답
    }
    // 3. PUT
    @RequestMapping( value = "/day03/red"  , method = RequestMethod.PUT )
    public String putRed(HttpServletRequest request ) throws IOException {
        String param1 = request.getParameter("param1");
        System.out.println("param1 = " + param1);
        return "정상응답";   // 2.응답
    }
    // 4. DELETE
    @RequestMapping( value = "/day03/red"  , method = RequestMethod.DELETE )
    public String deleteRed(HttpServletRequest request ) throws IOException {
        String param1 = request.getParameter("param1");
        System.out.println("param1 = " + param1);
        return "정상응답";   // 2.응답
    }
}