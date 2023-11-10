package ezenweb.model.repository;


import ezenweb.model.entity.ProductImgFileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductImgEntityRepository extends JpaRepository<ProductImgFileEntity, String> {
}
