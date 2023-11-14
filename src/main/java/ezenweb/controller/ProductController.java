package ezenweb.controller;

import ezenweb.model.dto.ProductCategoryDto;
import ezenweb.model.dto.ProductDto;
import ezenweb.service.FileService;
import ezenweb.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( "/product" )
public class ProductController {

    @Autowired
    ProductService productService;


    // 1. 카테고리 등록
    @PostMapping("/category")
    public boolean addCategory(@RequestBody ProductCategoryDto productCategoryDto){

        return productService.addCategory(productCategoryDto);
    }

    // 2. 카테고리 출력
    @GetMapping("/category")
    public List<ProductCategoryDto> printCategory(){

        return productService.printCategory();
    }

    // 3. 카테고리 수정
    @PutMapping("/category")
    public boolean updateCategory( @RequestBody ProductCategoryDto productCategoryDto){

        return productService.updateCategory(productCategoryDto );
    }

    // 4. 카테고리 삭제
    @DeleteMapping("/category")
    public boolean deleteCategory( @RequestParam int pcno ){
        return productService.deleteCategory( pcno );
    }


    // ===================================제품====================================================

    // 1. 제품 등록
    @PostMapping("/productPost")
    public boolean onProductAdd ( ProductDto productDto ){
        return productService.onProductAdd( productDto );
    }

    // 2. 제품 출력
    @GetMapping("/productGet")
    public List<ProductDto>  onProductPrintAll (  ){

        return productService.onProductPrintAll();
    }

    // 3. 제품 수정
    @PutMapping("/product")
    public boolean updateProduct ( @RequestBody ProductDto productDto ){

        return false;
    }

    // 4. 제품 삭제
    @DeleteMapping("/product")
    public boolean deleteProduct ( @RequestParam String pno ){

        return false;
    }




}
