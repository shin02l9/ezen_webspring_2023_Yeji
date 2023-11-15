package ezenweb.service;

import ezenweb.model.dto.ProductCategoryDto;
import ezenweb.model.dto.ProductDto;
import ezenweb.model.dto.ProductImgDto;
import ezenweb.model.entity.ProductCategoryEntity;
import ezenweb.model.entity.ProductEntity;
import ezenweb.model.entity.ProductImgFileEntity;
import ezenweb.model.repository.ProductCategoryEntityRepository;
import ezenweb.model.repository.ProductEntityRepository;
import ezenweb.model.repository.ProductImgEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    ProductCategoryEntityRepository productCategoryEntityRepository;
    @Autowired
    ProductEntityRepository productEntityRepository;
    @Autowired
    ProductImgEntityRepository productImgEntityRepository;
    @Autowired
    FileService fileService;

    // 1. 카테고리 등록
    @Transactional
    public boolean addCategory(@RequestBody ProductCategoryDto productCategoryDto){

        // 1. DTO --> 엔티티
        // 2. 리포지토리 이용한 엔티티 세이브
        // 3. 성공시 true 실패시 false

        return productCategoryEntityRepository.save(
                ProductCategoryEntity.builder().
                        pcname(productCategoryDto.getPcname())
                        .build()
        ).getPcno()>= 1 ? true : false;
    }
    
    // 2. 카테고리 출력
    @Transactional
    public List<ProductCategoryDto> printCategory(){

        // 1. 모든 엔티티 호출 // 2. 모든 엔티티 리스트 -> dto 리스트 변환 // 3. dto 리스트 반환

        return productCategoryEntityRepository.findAll().stream().map(
                e ->{ return ProductCategoryDto.builder()
                        .pcno( e.getPcno() )
                        .pcname( e.getPcname() )
                        .build(); }
                ).collect(Collectors.toList());

    }
    
    // 3. 카테고리 수정
    @Transactional
    public boolean updateCategory(ProductCategoryDto productCategoryDto ){

        // 1. 수정할 엔티티 찾는다[ pcno ] // 2. 찾은 엔티티가 존재하면 수정 o 아니면 수정x // 3. 성공시 true 실패시 false

        ProductCategoryEntity productCategoryEntity =
                toEntity(productCategoryDto.getPcno());
        if(productCategoryEntity != null){
            productCategoryEntity.setPcname( productCategoryDto.getPcname() );
            return true;
        }

        return false;
    }
    
    // 4. 카테고리 삭제
    @Transactional
    public boolean deleteCategory( int pcno ){
        // 1. 삭제할 엔티티 찾는다[pcno] // 2. 찾은 엔티티가 존재하면 삭제o 아니면 삭제x // 3. 성공시true 실패시 false
        ProductCategoryEntity productCategoryEntity = toEntity( pcno );
        if( productCategoryEntity != null ){ productCategoryEntityRepository.delete(productCategoryEntity); return  true; }
        return false;
    }

    // 5. 부가적인 엔티티 검색용 함수
    public ProductCategoryEntity toEntity (int pcno ){

        Optional<ProductCategoryEntity> productCategoryEntityOptional =
                productCategoryEntityRepository.findById( pcno );
        return productCategoryEntityOptional.isPresent() ? productCategoryEntityOptional.get() : null ;

    }

    // ==============================제품=========================================================

    // 1. 제품 등록
    public boolean onProductAdd ( ProductDto productDto ){
        System.out.println("ProductService.onProductAdd");
        System.out.println("productDto = " + productDto);

        // 1. 카테고리 엔티티 준비
        ProductCategoryEntity productCategory
                = productCategoryEntityRepository.findById( productDto.getPcno() ).get();

        // 2. 제품 엔티티를 생성
        // 단방향 : 제품 엔티티에 카테고리 엔티티 넣어주기
        // 양방향 : 제품 엔티티에 이미지 엔티티 리스트 넣어주기

            // 2-1. 제품 등록 엔티티 생성
        ProductEntity productEntity = ProductEntity.builder()
                // 카테고리번호-등록날짜
                .pno(productCategory.getPcno()+"-"+
                        LocalDateTime.now()
                                .format(DateTimeFormatter
                                        .ofPattern("yyyyMMddHHmmss")))
                .pname( productDto.getPname() )
                .pcommnet( productDto.getPcomment() )
                .pprice( productDto.getPprice() )
                .pstock( productDto.getPstock() )
                .productCategory( productCategory )
                .imgFileEntities( new ArrayList<>())
                .build();
            // 2-2. 제품 이미지 등록 [ 첨부파일 여러개 ]
        productDto.getFileList().stream().map(
            file -> {
                return fileService.fileUpload( file ); // 업로드에 결과를 리턴
        }).collect( Collectors.toList()) // 업로드 된 파일의 UUID(식별파일명)이 반환되는데 map이니까 여러개로 온다
                .forEach( uuidflie -> {
                    productEntity.getImgFileEntities().add(
                            ProductImgFileEntity.builder()
                                    .uuidFlieName( uuidflie )
                                    .realFlieName( uuidflie.split("_")[1])
                                    .productEntity( productEntity )
                                    .build()
                    );
                });
        productEntityRepository.save( productEntity );

        return true;
    }

    // 2. 제품 출력
    public List<ProductDto>  onProductPrintAll (  ){

        // 1. 모든 제품 호출
            // JPA 정렬
        List<ProductEntity> productEntities
                = productEntityRepository.findAll( Sort.by( Sort.Direction.DESC, "cdate"));

        // 2. 모든 제품의 엔티티를 DTO로 변환해서 반환하기
        return productEntities.stream().map( (p) ->{
            return ProductDto.builder()
                    .pno( p.getPno() )
                    .pname( p.getPname() )
                    .pcomment( p.getPcommnet() )
                    .pstate( p.getPstate() )
                    .pstock( p.getPstock() )
                    .pprice( p.getPprice() )
                    .categoryDto( ProductCategoryDto.builder()
                                        .pcno( p.getProductCategory().getPcno())
                                        .pcname( p.getProductCategory().getPcname())
                                        .build() )
                    .imgDtoList(
                        p.getImgFileEntities().stream().map( (img) ->{
                            return  ProductImgDto.builder()
                                    .realFileName( img.getRealFlieName() )
                                    .uuidFileName( img.getUuidFlieName() )
                                    .build();
                        }).collect(Collectors.toList())
                    )
                    .build();
        }).collect(Collectors.toList());
    }

    // 3. 제품 수정
    public boolean updateProduct ( ProductDto productDto ){

        return false;
    }

    // 4. 제품 삭제
    public boolean deleteProduct ( String pno ){

        return false;
    }

    // 차트 =====================================================================


    // 5. 막대 차트
    @Transactional
    public List<Map<Object,Object>> getBarChart(){

        return productEntityRepository.findByBarChartData();
    }

    // 6. 원형 차트
    @Transactional
    public List<Map<Object,Object>> getPieChart(){

        return productEntityRepository.findByPieChartData();
    }

}
