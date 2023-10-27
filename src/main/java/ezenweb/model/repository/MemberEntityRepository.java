package ezenweb.model.repository;



import ezenweb.model.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberEntityRepository  extends JpaRepository<MemberEntity, Integer > {
    // 인터페이스
    // 이메일을 이용한 엔티티/레코드 검색 select * from members where memail = 변수
    // 반환자료형 추상 메소드 ( 매개변수 )
    // * MemberEntity findBy필드명 ( 매개변수 )

    // 1. 동일한 이메일이 있는지 찾아보기 위해서 선언한 추상 메소드
    Optional<MemberEntity> findByMemail(String memail); // = select * from members where memail = 변수
    // 2. 동일한 이메일이 있을때 'true' 없을때 'false' 반환 : 엔티티의 존재 여부 검색
    boolean existsByMemail( String memail );
    // 3. 조건에 and/or 이 있을 때 이메일과 이름이 같을 때
    //MemberEntity findByNnameAndMemail( String memail, String mname );
}

