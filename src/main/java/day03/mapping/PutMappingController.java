package day03.mapping;


import day03.ParamDto;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/day03/put")

public class PutMappingController {
    //1.
    @PutMapping("/method1")
    public  ParamDto method1(@RequestBody ParamDto paramDto){
        System.out.println("PutMappingController.method1");
        System.out.println("paramDto = " + paramDto);
        return paramDto;    // 자동으로 DTO를 application/json 타입의 형식으로 paramDto를 응답
    }
    @PutMapping("/method2")
    public Map<String , String> method2(@RequestBody Map<String,String> map){
        System.out.println("PutMappingController.method2");
        System.out.println("map = " + map);
        return map;  // 자동으로 DTO를 application/json 타입의 형식으로 map 응답
    }
}