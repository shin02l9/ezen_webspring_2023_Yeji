package example.parking;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/car")
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping("/index")
    public Resource getIndex(){

        return new ClassPathResource("template/index.html");
    }

    @PostMapping("/do")
    public boolean doPost(@RequestBody CarDto carDto){
        boolean result = carService.doPost(carDto);
        return result;
    }

    @GetMapping("/do")
    public List<CarDto> doGet(){
        List<CarDto> result = carService.doGet();
        return result;
    }

    @PutMapping("/do")
    public boolean doPut(@RequestBody CarDto carDto){
        boolean result = carService.doPut(carDto);
        return result;

    }

    @DeleteMapping("/do")
    public boolean doDelete(@RequestParam int cno){

        boolean result = carService.doDelete(cno);
        return result;
    }



}
