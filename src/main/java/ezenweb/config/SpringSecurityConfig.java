package ezenweb.config;


import ezenweb.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    // -- 시큐리티 관련 메소드 커스텀 하기 --
        // 1. 해당클래스에 상속받기 [ extends WebSecurityConfigurerAdapter ]
        // 2. 커스텀 할 메소드 오버라이딩 하기
                // configure(HttpSecurity http)

    // p.685 : configure(HttpSecurity http) HTTP 관련된 보안 담당하는 메소드
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //super.configure(http);
        // ▲ 위 코드의 주석을 풀면 로그인 창이 뜨고 주석처리하면 안뜸 (보안이 풀림)

        // 0. 인증(로그인)된 인가(권한/허가) 통해 페이지 접근 제한
        http.authorizeHttpRequests() // 1. 인증된 권한에 따른 HTTP 요청 제한
                .antMatchers("/info").hasRole("USER")   // 인증된 권한 중에 ROLE_USER 권한명 일치
                .antMatchers("/**").permitAll();        // 모든 페이지는 권한 모두 허용


        // 1. 로그인 ( 인증 )
        http.formLogin()                             // 1. 시큐리티 로그인 사용
                .loginPage("/login")                 // 2. 직접 만든 페이지 사용을 위한 HTTP 주소 정의
                .loginProcessingUrl("/member/login")    // 3. 인증처리 요청을 보낼때 사용할 HTTP 주소
                // 시큐리티 사용하기전에 정의한 로그인 로그아웃 함수 없애기
                // HTTP "/member/login" POST 요청시 -----> MemberService의 loadUserByUsername 으로 이동한다.
                .defaultSuccessUrl("/")                     // 4. 만약에 로그인 성공시 이동할 HTTP 주소
                .failureUrl("/login")   // 5. 만약에 로그인 실패시 이동할 HTTP 주소
                .usernameParameter("memail")                // 6. 로그인시 입력받은 아이디의 변수명 정의
                .passwordParameter("mpassword");            // 7. 로그인시 입력받은 비밀번호의 변수명 정의
        // 2. CSRF 커스텀
        //http.csrf().disable(); // 모두 HTTP post/put 에서 csrf 사용 안함
        http.csrf().ignoringAntMatchers("/member/login"); // 특정 HTTP 경로에서만 csrf 사용안함
        http.csrf().ignoringAntMatchers("/member/do"); // 특정 HTTP 경로에서만 csrf 사용안함

        // 3. 로그아웃 [ 시큐리티 사용전에 컨트롤러와 서비스에 로그아웃 메소드 제거하기 ]
        http.logout()                                // 1. 로그인 인증 로그아웃 처리
                .logoutRequestMatcher( new AntPathRequestMatcher("/member/logout"))   // 2. 로그아웃 처리할 HTTP 주소 정의
                .logoutSuccessUrl("/")               // 3. 로그아웃 성공했을때 이동할 HTTP 주소
                .invalidateHttpSession( true );      // 4. 로그아웃 할때 HTTP 세션 모두 초기화

    }

    @Autowired
    private MemberService memberService;
    // p.689 : configure(AuthenticationManagerBuilder auth) 웹 시큐리티의 인증을 담당하는 메소드
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // super.configure(auth);
        auth.userDetailsService( memberService )
                .passwordEncoder( new BCryptPasswordEncoder() );
        // auth.userDetailsService( userDetailsService구현체 ).passwordEncoder( 사용할 암호화 객체 );

    }
}
