package ezenweb.model.repository;



import ezenweb.model.entity.BoardEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BoardEntityRepository
        extends JpaRepository<BoardEntity, Integer > {
    // extends JpaRepository<조작할엔티티, 조작할엔티티의PK필드타입 > {}

    // 추상메소드를 이용한 엔티티 검색
    // 1. 기본적으로 제공하는 함수는 마우스 우클릭으로 메소드 재정의 클릭 후 확인 가능하다.
            // 반환 타입을 잘 확인 해서 사용하기
            // Ex. 해당하는 제목의 엔티티 찾기

    // 2. 커스텀 하기 !
    //Optional<BoardEntity> findbyBtitle( String btitle );
    boolean existsByBtitle( String btitle);
    //List<BoardEntity> findByBtitle( String btitle, Pageable pageable);
    // + 페이징 처리를 원하면
    //Page<BoardEntity> findbyBtitle(String btitle, Pageable pageable);

    // + 실제 쿼리 ( MySQL )  사용하기
        // nativeQuery = true 실제 쿼리문과 같다는 뜻 , false 이거나 생략을 하면 실제 쿼리가 아니라 jpql이라는 자바랑 MySQL과 짬뽕된 언어를 사용해야함...
    //@Query( value = "SQL 문 작성하는 구역", nativeQuery = true);
    //@Query( value = "select * from board", nativeQuery = true) == findAll
    //@Query( value = "select * from board where bno = :bno", nativeQuery = true) == findById
    //@Query( value = "select * from board where btitle = :btitle", nativeQuery = true) == findByBtitle
    //@Query( value = "select * from board where bcontent = :bcontent", nativeQuery = true) == findByBcontent
    //if( keyword = '', "전체검색", if( key = 'btitle', "타이틀검색", if( key = 'bcontent', "내용검색") ) )
    @Query( value = " select * from board where " +
            " if( :keyword = '', true, " +                          // 전체 검색
            " if( :key = 'btitle', btitle like %:keyword%, " +      // 제목 검색
            " if( :key = 'bcontent', bcontent like %:keyword% , true ))) " + // 내용 검색
            " order by udate desc " // 날짜 기준 내림차순 정렬 ( 최신순 )
            , nativeQuery = true)
    Page<BoardEntity> findBySearch(String key, String keyword, Pageable pageable);




}

