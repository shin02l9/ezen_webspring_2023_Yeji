package ezenweb.controller;


import ezenweb.model.dto.BoardDto;
import ezenweb.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardService boardService;

    // 1. [C] 게시글 등록
    @PostMapping("/do")
    public boolean postBoard( @RequestBody BoardDto boardDto ) {
        return boardService.postBoard(boardDto);
    };

    // 2. [R] 게시글 출력 (전체)
    @GetMapping("/do")
    public List<BoardDto> getBoard( ) {
        return boardService.getBoard();
    };

    // 3. [U] 게시글 수정
    @PutMapping("/do")
    public boolean putBoard( @RequestBody BoardDto boardDto ) {
        return boardService.putBoard(boardDto);
    };

    // 4. [D] 게시글 삭제
    @DeleteMapping("/do")
    public boolean deleteBoard( @RequestParam int bno ) {
        return boardService.deleteBoard(bno);
    };


}
