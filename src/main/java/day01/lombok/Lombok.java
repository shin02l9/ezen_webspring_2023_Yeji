package day01.lombok;

import day01.consoleMvc.ConsoleDao;
import day01.consoleMvc.ConsoleDto;

import java.time.LocalDate;

public class Lombok {
    public static void main(String[] args) {

        // 룸북이 없을떄 DTO -------------------------------------------------------------
        // 1. 빈 생성자로 객체 생성
        ConsoleDto consoleDto1 = new ConsoleDto();

        // 2. 풀생성자로 객체 생성
        ConsoleDto consoleDto2 = new ConsoleDto( 0, "공부", LocalDate.now(), true );

        // 3. getter, setter 메소드 사용할 때
        consoleDto1.setTitle("공부하기");
        consoleDto1.getTitle();

        // 4. toString 사용할 때
        System.out.println( consoleDto1 );

        // 5.
            // 1. 빈생성자 또는 풀생성자가 아닌 새로운 생성자를 사용하고 싶을 때 { 정의해야만 가능 }
        //ConsoleDto consoleDto3 = new ConsoleDto( "공부", true );
            // 2. 생성자는 매개변수의 순서가 다르면 오류 발생
        //ConsoleDto consoleDto4 = new ConsoleDto( "공부", true, 0 , LocalDate.now() );

        // 룸북이 있을때 DTO -------------------------------------------------------------
        LombokDto lombokDto1 = new LombokDto();
        LombokDto lombokDto2 = new LombokDto( 0, "공부", LocalDate.now(), true );
        lombokDto1.setTitle("자바공부");
        lombokDto1.getTitle();
        System.out.println(lombokDto1);

        // ★빌더 패턴 : [복잡한 객체 생성 과정]을 위해 다양한 구성의 객체를 만들 수 있게 패턴[함수]을 지원
            // 위에 5번 두개 다 빨간줄 뜨는데 이건 안뜸 ㅎ;;ㅎ
            // 1. 없는 생성자인데 된다.
        LombokDto lombokDto3 = LombokDto.builder().title("공부1").finished(true).build();
            // 2. 순서가 다른데 된다.
        LombokDto lombokDto4 = LombokDto.builder()
                .finished(true)
                .title("공부2")
                .date(LocalDate.now())
                .build();
    }
}
