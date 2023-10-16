package ezenweb.service;


import ezenweb.model.dto.MemberDto;
import ezenweb.model.entity.MemberEntity;
import ezenweb.model.repository.MemberEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class MemberService {
    // Controller 요청 -> Service 요청 -> Repository
    // Controller <- 응답 Service <- 응답 Repository
    @Autowired
    private MemberEntityRepository memberRepository;


    // 1. [C] 회원 가입
    @Transactional
    public boolean postMember( MemberDto memberDto ) {
        System.out.println("memberDto = " + memberDto);
        
        // 1. dto -> entity 변경후 repository를 통해 insert 후 결과를 entity로 받기
        MemberEntity memberEntity = memberRepository.save( memberDto.toMemberEntity() );
        // 2. insert 된 엔티티 확인 후 성공/실패 유무 확인하기
            // 회원번호가 0보다 크면 auto-increment 적용 된거임 !
        if( memberEntity.getMno() >= 1 ) { return true; }
        return false;
    }

    // 2. [R] 회원정보 호출
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
    }

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
            // 4. 수정하기
            memberEntity.setMname( memberDto.getMname() );
            memberEntity.setMpassword( memberDto.getMpassword() );
            memberEntity.setMphone( memberDto.getMphone() );
            return true;
        }
        return false;
    }

    // 4. [D] 회원 탈퇴
    @Transactional
    public boolean deleteMember(int mno) {
        System.out.println("mno = " + mno);

        if ( memberRepository.existsById(mno) ) {
            memberRepository.deleteById(mno);
            return true; // 삭제 성공
        } else {
            return false; // 삭제 실패 (해당 ID가 존재하지 않음)
        }
    }

    // 5. 아이디 찾기
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

    // 7. 로그인
    public boolean login(MemberDto memberDto){
        System.out.println("memberDto = " + memberDto);
        List<MemberEntity> entity = memberRepository.findAll();
        if( entity.size() > 0 ) {
            for( MemberEntity e : entity ) {
                if( e.getMemail().equals(memberDto.getMemail())
                        && e.getMpassword().equals(memberDto.getMpassword()) ) {
                    return true;
                }
            }
        }
        return false;
    }

    // 8. 로그아웃
    public boolean logout(){
            // 사용 안했음.
        return false;
    }





}