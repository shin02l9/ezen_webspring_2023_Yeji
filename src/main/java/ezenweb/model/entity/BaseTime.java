package ezenweb.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// ┌> 공통된 어노테이션 정보 등을 부모 클래스로 선언하고 어노테이션 정보를 자식 클래스에게 제공할떄 사용함
@MappedSuperclass // 엔티티가 아님.. 여러 엔티티가 사용하는 필드에 대해 구성할때 | @CreatedDate, @LastModifiedDate 이 어노테이션들까지 자동으로 상속되지 않음 그래서 그걸 하려고 적은 어노테이션이다.
@EntityListeners( AuditingEntityListener.class) // JPA Auditing 이벤트 발생할때 감지 하겠다는 선언
@Getter @Setter
public class BaseTime {
    @CreatedDate
    public LocalDateTime cdate;    // 엔티티/레코드 생성날짜
    @LastModifiedDate
    public LocalDateTime udate;    // 엔티티/레코드 수정날짜


    // 1. 날짜 형변환 메소드 [자바 기준] // 구글링 키워드 : java localdatetime 형식 변환
    public String toStringForDate( LocalDateTime dateTime){
        return dateTime.toLocalDate().toString().equals( LocalDateTime.now().toLocalDate().toString() )
                ? dateTime.toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm:ss") )   // 오늘과 같으면 시간만 반환
                : dateTime.toLocalDate().format(DateTimeFormatter.ofPattern("yyyy:MM:dd") ); // 오늘과 다르면 날짜만 반환
    }
}

/*
    엔티티의 생성/수정 일지를 감지해서 자동 업데이트 해주는 클래스
    1. 어노테이션
        @MappedSuperclass    : JPA 엔티티 클래스들의 공통 필드 상속할 때 사용 하는 어노테이션 (부모 클래스의 매핑 정보를 자식 클래스에게 제공 )
        @EntityListeners( AuditingEntityListener.class) : 해당 클래스에서 엔티티 감지 기능
                         ㄴ> : @SpringBootApplication과 같은 위치에 @EnableJpaAuditing 주입 -> 이 두가지는 세트라 같이 사용해야 한다.★
                 @AuditingEntityListener.class : 감지이벤트 실행 ( insert, update 를 할때 )
                 @EnableJpaAuditing            : JPA Auditing 를 이용한 엔티티의 감지
        @CreatedDate         : 엔티티가 생성될 때 시간이 자동 저장/주입 된다.
        @LastModifiedDate    : 엔티티가 수정될 때 시간이 자동 저장/주입 된다.
*/