package ezenweb.model.repository;

import ezenweb.model.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Repository
public interface ProductEntityRepository extends JpaRepository<ProductEntity , String> {

    // 1. 제품별 재고 수
    @Query( value = "select pname, pstock from product"
            , nativeQuery = true)
    List<Map<Object,Object>> findByBarChartData();

    // 2. 카테고리별 제품 수
    @Query( value = "select pc.name, count(*) from product p" +
            " inner join productcategory pc on p.pcno = pc.pcno group by pc.pcname"
            , nativeQuery = true)
    List<Map<Object,Object>> findByPieChartData();



}
