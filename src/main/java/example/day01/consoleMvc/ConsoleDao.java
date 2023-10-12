package example.day01.consoleMvc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ConsoleDao {

    public Connection conn; // DB연동 객체
    public PreparedStatement ps; // 연동된 DB에서 SQL 조작하는 객체
    public ResultSet rs; // SQL 조작 결과를 가져오는 객체

    public ConsoleDao(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/springweb", "root", "0000");
            System.out.println(" 연동 성공 ");
        } catch ( Exception e ){
            System.out.println(" 연동 실패 " + e);
        }
    }

    public List<ConsoleDto> doGet(){
        List<ConsoleDto> list = new ArrayList<>();
        try{
            String sql = "select * from todo";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery(); // s->q , i/d/u -> u

            while( rs.next() ) {
                ConsoleDto dto = new ConsoleDto(
                  rs.getInt( 1 ),
                  rs.getString(2),
                  LocalDate.parse( rs.getString(3)),
                  rs.getBoolean(4)
                );
                list.add(dto);
            }
        }catch( Exception e ){
            System.out.println(e);
        }
        return list;
    } // doGet e
    public boolean doPost(ConsoleDto dto){

        try{
            String sql = "insert into todo( title , dueDate , finished ) values ( ?, ?, ? )";
            ps = conn.prepareStatement(sql);
            ps.setString(1, dto.getTitle());
            ps.setString(2, dto.getDate().toString());
            ps.setBoolean(3, dto.isFinished());
            int count = ps.executeUpdate(); // s->q , i/d/u -> u
            if( count == 1 ){
                return true;
            }
        } catch ( Exception e ){
            System.out.println(e);
        }

        return false;
    } // doPost e
}
