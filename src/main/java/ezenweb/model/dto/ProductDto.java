package ezenweb.model.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class ProductDto {

    // ============ 등록 또는 출력용 ============
    private String pno;        // 제품번호
    private String pname;       // 제품명
    private String pcomment;    // 제품설명
    private int pprice;         // 제품가격
    private byte pstate;        // 제품상태
    private int pstock;         // 제품재고

    // ============ 등록용 ============
    // + 첨부파일 여러개 일때
    private List<MultipartFile> fileList; // 첨부파일 여러개
    private int pcno;         // 제품 카테고리번호

    // ============ 출력용 ============
    private ProductCategoryDto categoryDto;
    private List<ProductImgDto> imgDtoList;


}
