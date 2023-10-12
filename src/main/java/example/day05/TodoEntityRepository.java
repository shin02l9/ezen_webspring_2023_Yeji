package example.day05;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// 엔티티를 조작하는 리모컨 역할이다.
@Repository // 이건 저장소야 ! 라고 선언. 스프링컨테이너에 빈 등록
public interface TodoEntityRepository extends JpaRepository< TodoEntity, Integer > {

}
