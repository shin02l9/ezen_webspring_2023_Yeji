package example.day05;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service  // 이 클래스는 서비스야 ! 라는 선언
// 역할 : 실제 기능 구현
public class TodoService {

    @Autowired
    public TodoEntityRepository todoEntityRepository;

    public boolean doPost( TodoDto todoDto ){ // --------------------------------

        // 1. DTO 를 엔티티로 변환
            // 빌더 패턴 사용
            // 클래스명 객체명 = 클래스명.builder().저장할필드명(저장할 값).build();
        TodoEntity todoEntity = TodoEntity.builder()
                .tcontent( todoDto.getTcontent() )
                .tstate( todoDto.isTstate() )
                .build();
        // 2.  JpaRepository 를 이용한 엔티티 저장 [ insert 대체 ]
        todoEntityRepository.save( todoEntity );

        return false;
    }


    public List<TodoDto> doGet(){ // --------------------------------

        // 1. 모든 엔터티 호출 [ select 대체 ]
        List<TodoEntity> todoEntityList = todoEntityRepository.findAll();

        // 2. List<TodoEntity> 를 List<TodoDto> 로 변환하기
            // 반환한 것을 넣어둘 곳
        List<TodoDto> todoDtoList = new ArrayList<>();
            // 모든 todoEntityList를 다 꺼내서 todoDtoList에 넣기
        todoEntityList.forEach( (entity) -> {
            TodoDto todoDto = TodoDto.builder()
                    .tno( entity.getTno() )
                    .tcontent( entity.getTcontent() )
                    .tstate( entity.isTstate() )
                    .build();
            todoDtoList.add( todoDto );
        });
        return todoDtoList;

    }
                    // import javax.transaction.Transactional;
    @Transactional // 수정만 함수가 없어서 어노테이션 써야함 무조건무조건무조건 필수 수정할땐 무조건!! commit의 역할을 해줌
    public boolean doPut( TodoDto todoDto ){ // --------------------------------

        // 1. 수정할 엔티티를 찾기
        Optional<TodoEntity> todoEntity = todoEntityRepository.findById( todoDto.getTno() );
        // 2. Optional 객체에 엔티티 존재 여부 확인 [ 안정성 보장 ]
        if (todoEntity.isPresent()){
            // 3. 옵션 객체에 엔티티 꺼내기
            TodoEntity updateEntity = todoEntity.get();
            // 4. 엔티티 찾았으니 필드 수정
            updateEntity.setTstate( todoDto.isTstate() );
        }

        return false;
    }


    public boolean doDelete( int tno ){ // --------------------------------

        // 1. 삭제할 식별키를 가지고 해당 엔티티 삭제 [ delete 대체 ]
        todoEntityRepository.deleteById( tno );

        return false;
    }

}
