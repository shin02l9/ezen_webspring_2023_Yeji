package ezenweb.service;


import ezenweb.model.dto.MemberDto;
import ezenweb.model.entity.MemberEntity;
import ezenweb.model.repository.MemberEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class MemberService
        implements
        UserDetailsService, // 일반 회원 서비스 : loadUserByUsername 메소드 구현 [ 로그인을 처리하는 메소드 ]
        OAuth2UserService<OAuth2UserRequest, OAuth2User>  // OAuth2 회원 서비스 : loadUser 메소드 구현 [ 로그인 회원정보를 받는 메소드 ]
{
    // p.739  OAuth2 회원 ========================================================
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        // 1. 로그인에 성공한 OAuth2 계정의 사용자정보(동의항목)의 정보를 호출한다.
        OAuth2User oAuth2User = new DefaultOAuth2UserService().loadUser(userRequest);
        System.out.println("oAuth2User = " + oAuth2User);
        // 2. 인증결과( 카카오, 네이버, 구글 )
        // 2-1. 인증한 소셜 서비스 아이디 찾기
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        System.out.println("registrationId = " + registrationId);


        String memail = null;
        String mname = null;
        String mrole = null;

        // 2-2. 카카오 이면
        if(registrationId.equals("kakao")){
            //System.out.println( oAuth2User.getAttributes("Name").toString());
            System.out.println( oAuth2User.getAttributes());
            System.out.println( oAuth2User.getAuthorities());

            //memail = oAuth2User.getAttributes().get("email").toString();
            Map<String, Object > kakaoAccount = (Map<String, Object >)oAuth2User.getAttributes().get("kakao_account");
            Map<String, Object > profile = (Map<String, Object >)kakaoAccount.get("profile");
            mname = profile.get("nickname").toString(); // 카카오회원의 닉네임
            memail = kakaoAccount.get("email").toString();   // 카카오회원의 이메일
            Object[] objects = oAuth2User.getAuthorities().toArray();

            System.out.println( memail );
            System.out.println( mname );
            System.out.println( objects );

        }
        // 2-3. 네이버 이면
        if(registrationId.equals("naver")){
            Map<String, Object > response = (Map<String, Object >)oAuth2User.getAttributes().get("response");
            mname = response.get("nickname").toString();
            memail = response.get("email").toString();

        }
        // 2-4. 구글 이면
        if(registrationId.equals("google")){
            mname = oAuth2User.getAttributes().get("name").toString();
            memail = oAuth2User.getAttributes().get("email").toString();
        }

        // 3. 일반 회원이랑 oAuth2 회원이랑 로그인 처리 통합 => DTO를 같이 쓴다는 뜻
            // 권한 목록에 추가
        List<GrantedAuthority> 권한목록 = new ArrayList<>();
        권한목록.add(
                new SimpleGrantedAuthority("ROLE_"+registrationId)
        );
            // DTO 만들기
        MemberDto memberDto = MemberDto.builder()
                // oAuth2는 비밀번호를 알 수 없음.
                .memail( memail )
                .mname( mname )
                .권한목록( 권한목록 )
                .소셜회원정보( oAuth2User.getAttributes() )
                .build();

            // 만약에 처음 최초 접속한 회원이면 DB처리 아니면 X
        if( !memberRepository.existsByMemail(memail)){
            memberDto.setMrole("ROLE_USER");
            memberDto.setMpassword(new BCryptPasswordEncoder().encode(mname));
            memberRepository.save( memberDto.toMemberEntity() );
        } else {
            memberDto.setMrole( memberRepository.findByMemail( memail ).get().getMrole() );
        }
        // 권한 추가
        memberDto.get권한목록().add( new SimpleGrantedAuthority( memberDto.getMrole() ));

        return memberDto;
    }


    // p.687 일반 회원 ========================================================
        // 1. UserDetailsService 구현체
        // 2. 인증처리해주는 메소드 구현
        // 3. loadUserByUsername 메소드는 무조건 UserDetails 객체를 반환해야한다. 그렇지 않으면 로그인 실패
        // 4. UserDetails객체를 이용한 패드워드 검증과 사용자 권한을 확인하는 동장(메소드)

    // 빈에 등록되어있지 않아서 해당코드는 @Autowired를 사용하지 못한다.
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public UserDetails loadUserByUsername(String memail) throws UsernameNotFoundException {
        System.out.println("MemberService.loadUserByUsername");
        System.out.println("loadUserByUsername memail = " + memail);

//        // 예시1) 임의의 아이디와 패드워드 넣고 UserDetails 객체 만들기
//        UserDetails userDetails = User.builder()
//                .username("qweqwe") // 아이디
//                //.password("123123") // 암호화 없는 패드워드
//                .password( passwordEncoder.encode("123123")) // 암호화 있는 패스워드
//                .authorities("ROLE_USER") // 인가 정보
//                .build();
//
//        return userDetails;

        // p.684
        // 1. 사용자의 아이디 만으로 사용자 정보를 로딩 [ 불러오기 ]
        Optional<MemberEntity> entity = memberRepository.findByMemail(memail);

            // 1-1. 없는 아이디이면
                // throw : 예외처리 던지기 // new UsernameNotFoundException(); 예외클래스
            if( !entity.isPresent() ){
                throw new UsernameNotFoundException("없는 아이디입니다.");
            }
        // 2. 로딩된 사용자의 정보를 이용해서 패스워드를 검증
            // 2-1. 권한 목록 추가
        List<GrantedAuthority> 권한목록 = new ArrayList<>();
        권한목록.add(
                new SimpleGrantedAuthority(entity.get().getMrole() )
        );
        // DTO 만들기
        MemberDto memberDto = MemberDto.builder()
                // oAuth2는 비밀번호를 알 수 없음.
                .memail( entity.get().getMemail() )
                .mname( entity.get().getMname() )
                .mpassword(entity.get().getMpassword())
                .권한목록( 권한목록 )
                .build();
        return memberDto;
    }


    // ----------------------------------------------------

    // Controller 요청 -> Service 요청 -> Repository
    // Controller <- 응답 Service <- 응답 Repository
    @Autowired
    private MemberEntityRepository memberRepository;

    // ---------------------------------------------------------------------------------

    // 1. [C] 회원 가입
    @Transactional
    public boolean postMember( MemberDto memberDto ) {
        System.out.println("memberDto = " + memberDto);

        // ------------------------암호화----------------------------
            // 입력받은 비밀번호를 암호화해서 다시 memberDto에 저장한다.
         memberDto.setMpassword( passwordEncoder.encode( memberDto.getMpassword()));

        // ----------------------------------------------------


        // 1. dto -> entity 변경후 repository를 통해 insert 후 결과를 entity로 받기
        MemberEntity memberEntity = memberRepository.save( memberDto.toMemberEntity() );
        // 2. insert 된 엔티티 확인 후 성공/실패 유무 확인하기
            // 회원번호가 0보다 크면 c
        if( memberEntity.getMno() >= 1 ) { return true; }
        return false;
    }

    // 1-2. 이메일 중복검사
    @Transactional
    public boolean checkEmail(String memail){
        return memberRepository.existsByMemail(memail);
    }

    /*// 2. [R] 회원정보 호출 -> 세션을 구현 안했을 때 ( 하단에 2번 함수 새로 정의한 것 있음 )
    @Transactional
    public MemberDto getMember(int mno) {
        System.out.println("mno = " + mno);

        // 1. 회원번호를 조회해서 값을 가져온다.
            // 그런데 만약에 조회되는 회원이 없어서 null이 되면 오류가 발생하니 Optional 으로 한번 감싸기
        Optional<MemberEntity> entity = memberRepository.findById(mno);
        // 2. 안에 값이 있다면 dto로 변환하기
        if( entity.isPresent() ) {
            MemberEntity memberEntity = entity.get();
            return memberEntity.toMemberDto();
        }
        return null;
    }*/



    // 3. [U] 회원정보 수정
    @Transactional
    public boolean putMember( MemberDto memberDto ) {
        System.out.println("memberDto = " + memberDto);
        // 1. 수정할 엔티티 찾기
        Optional<MemberEntity> entity = memberRepository.findById( memberDto.getMno() );

        // 2. 찾은 값이 존재 한다면
        if( entity.isPresent() ) {
            // 3. 가져오기
            MemberEntity memberEntity = entity.get();
            // 기존비밀번호 동일한지 확인
            System.out.println(" 기존 비밀번호 확인 ");
            String passwqrd1 = memberDto.getMpassword();
            String passwqrd2 = entity.get().toMemberDto().getMpassword();

            if( passwqrd1.equals(passwqrd2) ){
                // 4. 수정하기
                memberEntity.setMname( memberDto.getMname() );
                memberEntity.setMpassword( memberDto.getMpasswordnew() );
                memberEntity.setMphone( memberDto.getMphone() );

                return true;
            } else {
                System.out.println(" 기존 비밀번호 불일치 ");
            }
        }
        return false;
    }

    // 4. [D] 회원 탈퇴
    @Transactional
    public boolean deleteMember(int mno) {
        System.out.println("mno = " + mno);

        if ( memberRepository.existsById(mno) ) {
            memberRepository.deleteById(mno);
            //logoutT();
            return true; // 삭제 성공
        } else {
            return false; // 삭제 실패 (해당 ID가 존재하지 않음)
        }
    }

    // ---------------------------------------------------------------------------------

    // 5. 아이디 찾기 ( 이름이랑 휴대폰 번호로 찾기 )
    public String findMemberId( String mname, String mphone ){
        System.out.println("mname = " + mname + ", mphone = " + mphone);
        List<MemberEntity> entity = memberRepository.findAll();
        if( entity.size() > 0 ) {
            for( MemberEntity e : entity ) {
                if( e.getMname().equals(mname) && e.getMphone().equals(mphone) ) {
                    return e.getMemail();
                }
            }
        }
        return null;
    }

    // 6. 비밀번호 찾기
    public String findMemberPw( String memail, String mphone ){
        System.out.println("memail = " + memail + ", mphone = " + mphone);
        List<MemberEntity> entity = memberRepository.findAll();
        if( entity.size() > 0 ) {
            for( MemberEntity e : entity ) {
                if( e.getMemail().equals(memail) && e.getMphone().equals(mphone) ) {
                    return e.getMpassword();
                }
            }
        }
        return null;
    }

//    // 7. 로그인
//    public boolean login(MemberDto memberDto){
//        System.out.println("memberDto = " + memberDto);
//        List<MemberEntity> entity = memberRepository.findAll();
//        if( entity.size() > 0 ) {
//            for( MemberEntity e : entity ) {
//                if( e.getMemail().equals(memberDto.getMemail())
//                        && e.getMpassword().equals(memberDto.getMpassword()) ) {
//                    return true;
//                }
//            }
//        }
//        return false;
//    }
//
//    // 8. 로그아웃
//    public boolean logout(){
//            // 사용 안했음.
//        return false;
//    }

    // 강사님이 하신 방법 (로그인, 로그아웃) ======================================
//    // 효율적인 방법은 아니지만 배운걸 토대로 하려면 !
//
//    @Autowired
//    private HttpServletRequest Request;
//
//    // 로그인 [post]
//    public boolean loginT(MemberDto memberDto){
//        // 1. 입력받은 데이터(아이디,비밀번호) 검증하기
//        List<MemberEntity> entity = memberRepository.findAll();
//            // 2. 동일한 아이디 찾기
//            for(int i = 0; i < entity.size(); i++){
//                MemberEntity m = entity.get(i);
//                // 3. 만약 찾으면
//                if( m.getMemail().equals(memberDto.getMemail())
//                        && m.getMpassword().equals(memberDto.getMpassword()) ) {
//                    // 4. 세션 저장하기
//                    Request.getSession().setAttribute("loginT", m.toMemberDto() );
//                    return true;
//                }
//            }
//        return false;
//    }

//    // 로그아웃 [get]
//    public boolean logoutT(){
//        Request.getSession().setAttribute("logoutT",null);
//        return true;
//    }
//
//    // 2. [R] 회원정보 호출 -> 세션 구현 후 로그인 된 회원정보 호출하기
//    @Transactional
//    public MemberDto getMember( ) {
//        System.out.println(" 회원정보 호출 service 입장");
//        // 1. 세션 호출
//        Object session = Request.getSession().getAttribute("loginId");
//
//        // 2. 세션 검증
//        if( session != null ) {
//            Optional<MemberEntity> entity = memberRepository.findByMemail( ((MemberDto)session).getMemail() );
//            System.out.println(entity.get().toMemberDto());
//            return entity.get().toMemberDto();
//        }
//        return null;
//    }

    // 9. 로그인 상태 / 회원 정보 호출 ( 시큐리티 사용 시 )
    public MemberDto getMember(){
        // ! : 시큐리티 사용하기전에는 서블릿 세션을 이용한 로그인 상태 저장
        // 시큐리티 사용할때는 일단 서블릿 세션이 아니고 시큐리티 저장소 이용.
        System.out.println("시큐리티에 저장된 세션 정보 저장소 : "
                + SecurityContextHolder.getContext() );
        System.out.println("시큐리티에 저장된 세션 정보 저장소에 저장된 인증 : "
                + SecurityContextHolder.getContext().getAuthentication() );
        System.out.println("시큐리티에 저장된 세션 정보 저장소에 저장된 인증 호출 : "
                + SecurityContextHolder.getContext().getAuthentication().getPrincipal() );

        // 인증에 성공한 정보 호출 [ 세션 호출 ]
        Object o = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println( o.toString() );
        // 1. 만약에 인증이 실패 했을때/ 없을때
        if( o.equals("anonymousUser")){ return null; }
        // 2. 인증결과에 저장된 UserDetails로 타입변환
        UserDetails userDetails = (UserDetails)o;
            // 로그인 상태에 필요한 데이터 구성
        Optional<MemberEntity> memberEntity = memberRepository.findByMemail( userDetails.getUsername() );
        // 3. UserDetails의 정보를 memberDto에 담아서 반환
        return MemberDto.builder().memail( memberEntity.get().getMemail() ).mno( memberEntity.get().getMno() ).build();
    }
}
