package ezenweb.controller;


import ezenweb.model.dto.MemberDto;
import ezenweb.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

// IOC : 제어역전 ( 객체 관리를 스프링에게 위힘 = 왜?? 개발자가  쳔할려고! 협업할려고 ! 객체공유해서 쓸려고 !
// OC : 의존성 주입  ( 스프링이 객체를 관리하니까 )
@RestController
@RequestMapping("/member")
public class MemberController {
    // Controller 요청 -> Service
    // Controller <- 응답 Service
    @Autowired
    private MemberService memberService;

    // ---------------------------------------------------------------------------------

    // 1. [C] 회원 가입
    @PostMapping("/do")
    public boolean postMember( @RequestBody MemberDto memberDto ) {

        boolean result = memberService.postMember(memberDto);
        return result;
    }

    // 1-2. 이메일 중복검사
    @GetMapping("/checkEmail")
    public boolean checkEmail( @RequestParam String memail){

        return memberService.checkEmail(memail);
    }

    /*// 2. [R] 회원정보 호출 (1명의 회원정보) -> 세션을 구현 안했을 때
    @GetMapping("/do")
    public MemberDto getMember( @RequestParam int mno ) {
        MemberDto memberDto = memberService.getMember(mno);
        return memberDto;
    }*/

    // 2. [R] 회원정보 호출 (1명의 회원정보) -> 세션 구현 후 로그인된 회원정보 호출하기
    @GetMapping("/do")
    public MemberDto getMember( ) {
        System.out.println(" 회원정보 호출 controller 입장");
        return memberService.getMember();
    }

    // 3. [U] 회원정보 수정
    @PutMapping("/do")
    public boolean putMember( @RequestBody MemberDto memberDto ) {
        boolean result = memberService.putMember(memberDto);
        return result;
    }

    // 4. [D] 회원 탈퇴
    @DeleteMapping("/do")
    public boolean deleteMember( @RequestParam int mno ) {
        boolean result = memberService.deleteMember(mno);
        return result;
    }

    // ---------------------------------------------------------------------------------

    // 5. 아이디 찾기 ( 이름이랑 휴대폰 번호로 찾기 )
    @GetMapping("/findid")
    public String findMemberId( @RequestParam String mname, @RequestParam String mphone ){
        String memail = memberService.findMemberId( mname, mphone );
        return memail;
    }

    // 6. 비밀번호 찾기
    @GetMapping("/findpw")
    public String findMemberPw( @RequestParam String memail, @RequestParam String mphone ){
        String mpassword = memberService.findMemberPw( memail, mphone );
        return mpassword;
    }

    // 7. 로그인
    @PostMapping("/login")
    public boolean login(@RequestBody MemberDto memberDto , HttpSession httpSession){

        if(memberService.login( memberDto )) {
            System.out.println("로그인 성공");
            httpSession.setAttribute("loginId", memberDto );
            return true;
        }
        else {
            System.out.println("로그인 실패");
            return false;
        }
    }

    // 8. 로그아웃
    @GetMapping("/logout")
    public boolean logout( HttpSession httpSession){

        if( httpSession.getAttribute("loginId")!= null ){
            httpSession.removeAttribute("loginId");
            return true;
        } else { return false; }
    }

    // 강사님이 하신 방법 (로그인, 로그아웃) ======================================

    // 로그인 [post]
    @PostMapping("/loginT")
    public boolean loginT(@RequestBody MemberDto memberDto){
        boolean result = memberService.loginT( memberDto );
        return result;
    }

    // 로그아웃 [get]
    @GetMapping("/logoutT")
    public boolean logoutT( ){
        boolean result = memberService.logoutT( );
        return result;
    }


}
