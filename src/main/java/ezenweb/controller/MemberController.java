package ezenweb.controller;


import ezenweb.model.dto.MemberDto;
import ezenweb.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/member")
public class MemberController {
    // Controller 요청 -> Service
    // Controller <- 응답 Service
    @Autowired
    private MemberService memberService;


    // 1. [C] 회원 가입
    @PostMapping("/do")
    public boolean postMember( @RequestBody MemberDto memberDto ) {
        boolean result = memberService.postMember(memberDto);
        return result;
    }

    // 2. [R] 회원정보 호출 (1명의 회원정보)
    @GetMapping("/do")
    public MemberDto getMember( @RequestParam int mno ) {
        MemberDto memberDto = memberService.getMember(mno);
        return memberDto;
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

        MemberDto loginDto = MemberDto.builder()
                .memail( memberDto.getMemail())
                .mpassword( memberDto.getMpassword() )
                .build();

        if(memberService.login( memberDto )) {
            System.out.println("로그인 성공");
            httpSession.setAttribute("loginId", loginDto );
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



}
