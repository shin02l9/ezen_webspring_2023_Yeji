package ezenweb.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class SpringMvcViewConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //super.addViewControllers(registry);
        registry.addViewController( "/{spring:\\w+}").setViewName("forward:/");
        registry.addViewController( "/**/{spring:\\w+}").setViewName("forward:/");
        registry.addViewController( "/{spring:\\w+}/**{spring:?!(\\.js|\\.css)$}").setViewName("forward:/");
    }
}

/*
    원래 스프링MVC 아키텍처에서는
        controller가 view를 반환하는 작업을 진행 했다.
        문제점 : 스프링이 리액트와 통합 되었을때
            리액트 라우터 (Link, get), 스프링(get)
            스프링 안에 리액트가 포함 되므로 get 요청 시 스프링 매핑이 우선 처리된다.
        문제해결 : get 요청시 view를 찾을 때 controller가 resources로 이동하여 찾게끔 설정한다.

        1. 스프링 설정 클래스 @Configuration
        2. extends WebMvcConfigurerAdapter : MVC 아키테척 설정 커스텀 해주는 클래스
        3. 오른쪽 클릭 -> 생성 -> 오버라이딩
        4. addViewControllers 메소드 오버라이딩/ 재정의



*/