package example.phone;


import example.phone.PhoneEntity;
import example.phone.PhoneEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service // 역할 : 실제 기능 구현
public class PhoneService {

    @Autowired
    public PhoneEntityRepository phoneEntityRepository;

    public boolean doPost( PhoneDto phoneDto ){ // --------------------------------

        // 1. DTO 를 엔티티로 변환
            // 빌더 패턴 사용
            // 클래스명 객체명 = 클래스명.builder().저장할필드명(저장할 값).build();
        PhoneEntity phoneEntity = PhoneEntity.builder()
                .pname( phoneDto.getPname() )
                .pphone( phoneDto.getPphone() )
                .build();
        // 2.  JpaRepository 를 이용한 엔티티 저장 [ insert 대체 ]
        phoneEntityRepository.save( phoneEntity );

        return false;
    }


    public List<PhoneDto> doGet(){ // --------------------------------

        // 1. 모든 엔터티 호출 [ select 대체 ]
        List<PhoneEntity> phoneEntities = phoneEntityRepository.findAll();

        // 2. List<PhoneEntity> 를 List<PhoneDto> 로 변환하기
            // 반환한 것을 넣어둘 곳
        List<PhoneDto> phoneDtoList = new ArrayList<>();
            // 모든 phoneDtoList 다 꺼내서 phoneEntities 넣기
        phoneEntities.forEach( (entity) -> {
            PhoneDto phoneDto = PhoneDto.builder()
                    .pno( entity.getPno() )
                    .pname( entity.getPname() )
                    .pphone( entity.getPphone() )
                    .build();
            phoneDtoList.add( phoneDto );
        });
        return phoneDtoList;

    }

    @Transactional
    public boolean doPut( PhoneDto phoneDto ){ // --------------------------------

        // 1. 수정할 엔티티를 찾기
        Optional<PhoneEntity> phoneEntity = phoneEntityRepository.findById( phoneDto.getPno() );
        // 2. Optional 객체에 엔티티 존재 여부 확인 [ 안정성 보장 ]
        if (phoneEntity.isPresent()){
            // 3. 옵션 객체에 엔티티 꺼내기
            PhoneEntity updateEntity = phoneEntity.get();
            // 4. 엔티티 찾았으니 필드 수정
            updateEntity.setPname( phoneDto.getPname() );
            updateEntity.setPphone( phoneDto.getPphone() );
        }

        return false;
    }


    public boolean doDelete( int pno ){ // --------------------------------

        // 1. 삭제할 식별키를 가지고 해당 엔티티 삭제 [ delete 대체 ]
        phoneEntityRepository.deleteById( pno );

        return false;
    }

}
