package example.day05;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // 이 클래스는 컨트롤러야 ! 라는 선언
// 역할 : 매핑
@RequestMapping("/todo")
public class TodoController {

    @Autowired
    private TodoService todoService;


    @GetMapping("/index")
    public Resource getIndex(){
        return new ClassPathResource("templates/todo.html");

    }

    @PostMapping("")
    public boolean doPost( @RequestBody TodoDto todoDto ){
        boolean result = todoService.doPost( todoDto );
        return result;

    }

    @GetMapping("")
    public List<TodoDto> doGet(){
        List<TodoDto> todoDtos = todoService.doGet();
        return todoDtos;
    }

    @PutMapping("")
    public boolean doPut( @RequestBody TodoDto todoDto ){
        boolean result = todoService.doPut( todoDto );
        return result;
    }

    @DeleteMapping("")
    public boolean doDelete( @RequestParam int tno ){
       // @RequestParam 매개변수 1개로 받아와서 쓰는것 객체를 받으면 @RequestBody 쓰는거임

        boolean result = todoService.doDelete( tno );
        return result;
    }
}
