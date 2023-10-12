package example.day03.mapping;


import example.day03.ParamDto;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController // 해당 클래스를 컨트롤러임을 선언한다. // + ResponseBody
@RequestMapping("/dat03/delete")
public class DeleteMappingController {

    // 1.
    @DeleteMapping("/method1")
    public boolean method1(@RequestParam String param1 ){
        System.out.println("param1 = " + param1);
        return true;
    }

    // 2.
    @DeleteMapping("/method2")
    public boolean method2(ParamDto paramDto){
        System.out.println("paramDto = " + paramDto);
        return true;
    }

}
