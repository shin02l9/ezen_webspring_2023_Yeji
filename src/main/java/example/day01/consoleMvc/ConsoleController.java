package example.day01.consoleMvc;

import java.time.LocalDate;
import java.util.List;
public class ConsoleController {


    public List<ConsoleDto> doGet(){
        ConsoleDao dao = new ConsoleDao();
        List<ConsoleDto> result = dao.doGet();


        return result;
    } // doGet e


    public boolean doPost(String title){

        // 1. 인수 받아서 Dto 생성
        ConsoleDto dto = new ConsoleDto( 0, title, LocalDate.now(), true);
        // 2. Dao 에게 전달
        ConsoleDao dao = new ConsoleDao();
        boolean result = dao.doPost( dto );

        return result;
    } // doPost e

}
