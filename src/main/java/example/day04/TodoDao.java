package example.day04;

import org.springframework.stereotype.Component;

import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Component // Spring MVC 중에 해당 클래스를 Model 사용
public class TodoDao {

    public Connection conn; // DB연동 객체
    public PreparedStatement ps; // 연동된 DB에서 SQL 조작하는 객체
    public ResultSet rs; // SQL 조작 결과를 가져오는 객체

    // 비어있는 생성자에 DB 연동하기
    public TodoDao(){
        // SQL 연동
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/springweb", "root", "1234");
            System.out.println(" 연동 성공 ");
        } catch ( Exception e ){
            System.out.println(" 연동 실패 " + e);
        }
    }

    // 1. [C]
    // 매핑 안하는 이유 : 통신이 필요 없어서 !!!
    public boolean doPost(TodoDto todoDto){    // 요청 매개변수 : 입력받은 정보들 [ Dto ]
        System.out.println("TodoDao.doPost");
        System.out.println("todoDto = " + todoDto);
        String sql = "insert into todo( tcontent, tstate ) values( ?, ? )";
        try{
            ps = conn.prepareStatement(sql);
            ps.setString( 1, todoDto.getTcontent());
            ps.setBoolean( 2, todoDto.isTstate());
            int count = ps.executeUpdate();
            if( count == 1 ) return true;

        } catch ( Exception e ){
            System.out.println("e : " + e);
        }
        return false;
    }
    // 2. [R]
    public List<TodoDto> doGet(){     // 요청 매개변수 : 출력 필요한 정보들 [ x ]
        System.out.println("TodoDao.doGet");
        List<TodoDto> list = new ArrayList<>();
        String sql = "select * from todo";
        try{
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while ( rs.next() ){
                list.add(
                    TodoDto.builder()
                            .tno( rs.getInt("tno") )
                            .tcontent( rs.getString("tcontent") )
                            .tstate( rs.getBoolean("tstate") )
                            .build()
                );
            }
        } catch ( Exception e ){
            System.out.println("e : " + e);
        }
        return list;
    }
    // 3. [U]
    public boolean doPut(TodoDto todoDto){     // 요청 매개변수 : 수정 필요한 정보들 [ Dto ]
        System.out.println("TodoDao.doPut");

        String sql = "update todo set tstate = ? where tno = ? ";
        try{
            ps = conn.prepareStatement(sql);
            ps.setBoolean( 1, todoDto.isTstate());
            ps.setInt( 2, todoDto.getTno());
            int count = ps.executeUpdate();
            if( count == 1 ) return true;
        } catch ( Exception e ){
            System.out.println("e : " + e);
        }
        return false;
    }
    // 4. [D]
    public boolean doDelete(int tno ){  // 요청 매개변수 : 삭제 필요한 정보들 [ tno ]
        System.out.println("TodoDao.doDelete");

        String sql = "delete from todo where tno = ? ";
        try{
            ps = conn.prepareStatement(sql);
            ps.setInt( 1, tno);
            int count = ps.executeUpdate();
            if( count == 1 ) return true;
        } catch ( Exception e ){
            System.out.println("e : " + e);
        }
        return false;
    }

}
