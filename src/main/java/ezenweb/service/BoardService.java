package ezenweb.service;


import ezenweb.model.dto.BoardDto;
import ezenweb.model.dto.MemberDto;
import ezenweb.model.entity.BoardEntity;
import ezenweb.model.entity.MemberEntity;
import ezenweb.model.repository.BoardEntityRepository;
import ezenweb.model.repository.MemberEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BoardService {

    @Autowired
    private BoardEntityRepository boardEntityRepository;
    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberEntityRepository memberEntityRepository;

    // 1. [C] 게시글 등록
    @Transactional
    public boolean postBoard( BoardDto boardDto ) {
        System.out.println("boardDto = " + boardDto);

        /*
            MemberEntity                                BoardEntity
             [p]  mno                                       [p] bno
                                <--- 단방향 --               [f] mno    @ManyToOne  MemberEntity

                                ---- 양방향 -->
             @OneToMany(mappedBy = "memberEntity")
             []List<BoardEntity> boardEntityList

         */
        // 1. FK 키의 엔티티를 찾는다.
        // [ FK로 사용할 PK를 알고 있어야 있어야한다. 세션,매개변수 가져오기 ]
        // ================================= 단방향 ================================================= //
            // 1. 예 ) 로그인된 회원의 pk번호 호출
             MemberDto loginMemberDto = memberService.getMember();
             if( loginMemberDto == null ){
                 return false;
             }
            // 2. 회원pk번호를 가지고 pk엔티티 찾기
        Optional<MemberEntity> memberEntityOptional
                = memberEntityRepository.findById( loginMemberDto.getMno() );
            // 3. 유효성검사 [ 로그인이 안된상태 글쓰기 실패 ]
        if( !memberEntityOptional.isPresent() ){ return false;}

            // 4. 단방향 저장  [ 게시물 엔티티에 회원엔티티 넣어주기 ]
                // 1. 게시물 생성 [ fk에 해당하는 레코드 생성 ]
        BoardEntity boardEntity  = boardEntityRepository.save( boardDto.saveToBoardEntity() );
                // 2. 생성된 게시물에 작성자엔티티 넣어주기 [ fk 넣어주기 ]
        boardEntity.setMemberEntity( memberEntityOptional.get() );
        // ================================= 단방향 end ================================================= //



        // ================================= 양방향 ================================================= //
            // 5. 양방향 저장 [ 회원엔티티에 게시물 엔티티 넣어주기 ]
        memberEntityOptional.get().getBoardEntitiyList().add( boardEntity );
        // ================================= 양방향 end ================================================= //
        if( boardEntity.getBno() >= 1) { return true; } return false;

//        // 받아온 DTO를 엔터티로 바꾸고 저장하기
//        BoardEntity boardEntity = boardEntityRepository.save(boardDto.saveToBoardEntity());
//
//        // 응답하기 ( bno가 1보다 큰지 조건 거는 이유 : 0 이상이어야 auto-increment 적용 된거라서.
//        if( boardEntity.getBno() >= 1 ) { return true; }
//        else { return false; }

    }

    // 2. [R] 게시글 출력 (전체)
    @Transactional
    public List<BoardDto> getBoard( ) {

        // 모든 게시물 출력이니 일단 죄다 꺼내오기
        List<BoardEntity> boardEntities = boardEntityRepository.findAll();

        // 받아온 Entity를 Dto 로 변환하기
        List<BoardDto> resultList = new ArrayList<>();
        boardEntities.forEach( list -> {
            resultList.add( list.saveToBoardDto() );
        });
        return resultList;
    }

    // 2-2. [R] 게시글 출력 (개별)
    @Transactional
    public BoardDto getBoardOne( int bno ) {
        Optional<BoardEntity> boardEntity = boardEntityRepository.findById(bno);
        if( boardEntity.isPresent() ) {
            return boardEntity.get().printOneToBoardDto();
        }
        return null;
    };

    // 3. [U] 게시글 수정
    @Transactional
    public boolean putBoard( BoardDto boardDto ) {
        System.out.println("boardDto = " + boardDto);

        // bno를 이용해서 수정해야 할 게시물 찾기
        Optional<BoardEntity> entityOptional = boardEntityRepository.findById(boardDto.getBno());

        // 만약에 값이 검색 되었다면
        if( entityOptional.isPresent() ) {
            // 꺼내기
            BoardEntity boardEntity = entityOptional.get();
            // 수정하기
            boardEntity.setBtitle(boardDto.getBtitle());
            boardEntity.setBcontent(boardDto.getBcontent());
            boardEntity.setBfile(boardDto.getBfile());
            return true;
        }
        return false;

    }

    // 4. [D] 게시글 삭제
    @Transactional
    public boolean deleteBoard( int bno ) {
        System.out.println("bno = " + bno);

        if( boardEntityRepository.existsById(bno) ) {
            boardEntityRepository.deleteById(bno);
            return true;
        } else { return false; }

    }


}
