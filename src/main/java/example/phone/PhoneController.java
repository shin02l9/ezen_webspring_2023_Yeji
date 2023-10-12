package example.phone;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController// 역할 : 매핑
@RequestMapping("/phone")
public class PhoneController {

    @Autowired
    private PhoneService phoneService;


    @GetMapping("/index")
    public Resource getIndex(){
        return new ClassPathResource("templates/phone.html");

    }

    @PostMapping("")
    public boolean doPost( @RequestBody PhoneDto phoneDto ){
        boolean result = phoneService.doPost( phoneDto );
        return result;

    }

    @GetMapping("")
    public List<PhoneDto> doGet(){
        List<PhoneDto> phoneDtoList = phoneService.doGet();
        return phoneDtoList;
    }

    @PutMapping("")
    public boolean doPut( @RequestBody PhoneDto phoneDto ){
        boolean result = phoneService.doPut( phoneDto );
        return result;
    }

    @DeleteMapping("")
    public boolean doDelete( @RequestParam int pno ){
       // @RequestParam 매개변수 1개로 받아와서 쓰는것 객체를 받으면 @RequestBody 쓰는거임

        boolean result = phoneService.doDelete( pno );
        return result;
    }
}
