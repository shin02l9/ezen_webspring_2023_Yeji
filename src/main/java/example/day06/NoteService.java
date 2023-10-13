package example.day06;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NoteService {

    @Autowired
    private NoteEntityRepository noteRepository;


    // 1. [C]
    public boolean bWrite( NoteDto noteDto ) {
        // 1. dto -> entity 로 변경
        noteRepository.save( noteDto.toEntity() );

        return true;
    }
    // 2. [R]
    public List<NoteDto> bList() {
        // 1. 모든 엔티티 호출하기
        List<NoteEntity> listEntity = noteRepository.findAll();
        // 2. 모든 엔터티 리스트를 dto로 변환
        List<NoteDto> listDto = new ArrayList<>();
        listEntity.forEach( e -> listDto.add( e.toDto() ) );

        return listDto;
    }
    // 3. [U]
    @Transactional // 수정할땐 함수가 없어서 이 어노테이션이 필요함 이유 : 필드 수정 중간에 오류가 생겨버리면 어떤 필드는 수정되고 어떤필드는 안되고.. 그런 일이 생기면 아주 큰 문제임 그래서 모든 필드가 다 성공해야 커밋수정으로 되게 하는 역할 인듯!
    public boolean bUpdate( NoteDto noteDto ) {
        // 1. 수정할 pk번호에 해당하는 엔티티 찾기 ( 엔티티 그냥 주지......................
        Optional<NoteEntity> entity = noteRepository.findById( noteDto.getNo() );
        // 2. 포장안에 내용물이 있는지 체크 빈상자일수도 있으니까
        if( entity.isPresent() ) {
            // 있으면 꺼낸다.
            NoteEntity noteEntity = entity.get();
            // 3. 꺼낸 내용물을 수정한다.
            // 별도로 수정함수가 없고 엔티티 객체에 필드를 수정하면 DB도 같이 수정된다.
            // 이유는 이미 매핑이 된 상태라서 그렇다
            noteEntity.setTitle( noteDto.getTitle() );
            noteEntity.setWriter( noteDto.getWriter() );
            return true;
        }
        return false;
    }
    // 4. [D]
    public boolean bDelete( int no ) {

        noteRepository.deleteById( no );
        return true;
    }

}
