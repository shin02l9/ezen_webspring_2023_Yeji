package ezenweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing     // JPA Auditing 기능을 사용/실행 하겠다는 선언
@SpringBootApplication // 스프링을 실행해주는 어노테이션 (@ComponentScan, Restful, 톰캣 내장으로 지원)
public class EzenWebStart { // 이걸 실행 한다고 해서 클래스만 실행 되는게 아니라 주입된 모든 클래스가 같이 실행 되는 것
    public static void main(String[] args) {
        SpringApplication.run( EzenWebStart.class );
    }

}
