package ezenweb.service;


import ezenweb.model.dto.BoardDto;
import ezenweb.model.entity.BoardEntity;
import ezenweb.model.repository.BoardEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BoardService {

    @Autowired
    private BoardEntityRepository boardEntityRepository;

    // 1. [C] 게시글 등록
    @Transactional
    public boolean postBoard( BoardDto boardDto ) {
        System.out.println("boardDto = " + boardDto);

        // 받아온 DTO를 엔터티로 바꾸고 저장하기
        BoardEntity boardEntity = boardEntityRepository.save(boardDto.saveToBoardEntity());

        // 응답하기 ( bno가 1보다 큰지 조건 거는 이유 : 0 이상이어야 auto-increment 적용 된거라서.
        if( boardEntity.getBno() >= 1 ) { return true; }
        else { return false; }

    };

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

    };

    // 4. [D] 게시글 삭제
    @Transactional
    public boolean deleteBoard( int bno ) {
        System.out.println("bno = " + bno);

        if( boardEntityRepository.existsById(bno) ) {
            boardEntityRepository.deleteById(bno);
            return true;
        } else { return false; }

    };


}
