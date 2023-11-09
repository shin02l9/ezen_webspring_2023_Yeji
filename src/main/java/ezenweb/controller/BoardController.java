package ezenweb.controller;


import ezenweb.model.dto.BoardDto;
import ezenweb.model.dto.PageDto;
import ezenweb.service.BoardService;
import ezenweb.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardService boardService;
    @Autowired
    private FileService fileService;

    // 1. [C] 게시글 등록 ------------------------------------------------------------
    @PostMapping("/do")
    public boolean postBoard( BoardDto boardDto ) {

        return boardService.postBoard(boardDto);
    }

    // 2. [R] 게시글 출력 (전체) ------------------------------------------------------------
    @GetMapping("/do")
    public PageDto getBoard(@RequestParam int page,
                            @RequestParam String key,
                            @RequestParam String keyword,
                            @RequestParam int view) {

        return boardService.getBoard(page, key, keyword, view);
    }

    // 2-2. [R] 게시글 출력 (개별) ------------------------------------------------------------
    @GetMapping("/doOne")
    public BoardDto getBoardOne( @RequestParam int bno ) {

        return boardService.getBoardOne(bno);
    }

    // 3. [U] 게시글 수정 ------------------------------------------------------------
    @PutMapping("/do")
    public boolean putBoard( BoardDto boardDto ) {

        return boardService.putBoard(boardDto);
    }

    // 4. [D] 게시글 삭제 ------------------------------------------------------------
    @DeleteMapping("/do")
    public boolean deleteBoard( @RequestParam int bno ) {

        return boardService.deleteBoard(bno);
    }

    // 5. 첨부파일 다운로드  ------------------------------------------------------------
    @GetMapping("/fileDownload")
    public void fileDownload( @RequestParam String UUIDfileName  ){
        fileService.fileDownload(UUIDfileName);

    }


}
