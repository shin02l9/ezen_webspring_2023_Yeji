package day04;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*  메타 어노테이션 ? : 실행 또는 컴파일 했을때 사용 방법에 대해 정의 */
/* 중요한 어노테이션
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan [ @Controller,
*/
@SpringBootApplication // 모든 컴포넌트 들을 빈에 등록한다.
public class AppStart {
    public static void main(String[] args) {
        SpringApplication.run( AppStart.class );

    }

    /*
        - 정적 파일 생성 위치
        스프링이 view 파일들을 찾는 위치 resources 폴더
        HTML : resorces -> templates - html 파일
        JS/CSS/Image : resorces -> static -> JS/CS/Images

        - JSP 프로젝트와 SPRING 프로젝트의 정적파일 경로 차이
            - JSP는 패키지의 경로와 파일명이 곧 URL
            - SPRING는 정적파일 호출하는 URL 매핑
                매핑 후 Resource  타입 반환

     */

}
