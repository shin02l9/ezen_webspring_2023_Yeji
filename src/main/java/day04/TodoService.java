package day04;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // Spring MVC 중에 해당 클래스를 Service 사용 : 실질적인 기능을 처리하는 기능
public class TodoService {

    @Autowired
    private TodoDao todoDao;

    // 1. [C]
    // 매핑 안하는 이유 : 통신이 필요 없어서 !!!
    public boolean doPost(TodoDto todoDto){    // 요청 매개변수 : 입력받은 정보들 [ Dto ]
        System.out.println("TotoService.doPost");
        System.out.println("todoDto = " + todoDto);
        return todoDao.doPost( todoDto );
    }
    // 2. [R]
    public List<TodoDto> doGet(){     // 요청 매개변수 : 출력 필요한 정보들 [ x ]
        System.out.println("TotoService.doGet");
        return todoDao.doGet();
    }
    // 3. [U]
    public boolean doPut(TodoDto todoDto){     // 요청 매개변수 : 수정 필요한 정보들 [ Dto ]
        System.out.println("TotoService.doPut");
        return todoDao.doPut( todoDto );
    }
    // 4. [D]
    public boolean doDelete(int tno ){  // 요청 매개변수 : 삭제 필요한 정보들 [ tno ]
        System.out.println("TotoService.doDelete");
        return todoDao.doDelete( tno );
    }



}
