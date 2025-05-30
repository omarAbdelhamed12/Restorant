package com.spring.boot.controller;

import com.spring.boot.dto.ProductDto;
 import com.spring.boot.service.ProductService;
import jakarta.transaction.SystemException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/product")
@CrossOrigin("http://localhost:4200")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/getAll")
    public ResponseEntity<List<ProductDto>> findAllProductDto() {
        return ResponseEntity.ok(productService.findAllProductDto());
    }

    @GetMapping("/allProductByCategoryId")
    public ResponseEntity<List<ProductDto>> allProductByCategoryId(@RequestParam Long categoryId)  {
        return ResponseEntity.ok(productService.getProductsByCategoryId(categoryId));
    }
    @PostMapping("/creatProduct")
    public ResponseEntity<ProductDto> createProduct(@RequestBody @Valid ProductDto productDto)   {
        return ResponseEntity.created(URI.create("/creatProduct")).body(productService.createProduct(productDto));
    }
    @PostMapping("/saveListOfProducts")
    public ResponseEntity<List<ProductDto>> saveListOfProducts(@RequestBody @Valid List<ProductDto> productDtoList)  {
        return ResponseEntity.created(URI.create("/saveListOfProducts")).body(productService.saveListOfProduct(productDtoList));
    }
    @PutMapping("/updateProduct")
    public ResponseEntity<ProductDto> updateProduct(@RequestBody @Valid ProductDto productDto)   {
        return ResponseEntity.ok(productService.updateProduct(productDto));
    }
    @PutMapping("/updateListOfProducts")
    public ResponseEntity<List<ProductDto>> updateListOfProducts(@RequestBody @Valid List<ProductDto> productDtoList)  {
        return ResponseEntity.ok(productService.updateListOfProduct(productDtoList));
    }
    @DeleteMapping("/deleteProduct")
    public ResponseEntity<Void> deleteProduct(@RequestParam("id") Long id) throws SystemException {
       return productService.deleteProductById(id) ?
               ResponseEntity.noContent().build() :
               ResponseEntity.notFound().build();
    }

    @DeleteMapping("/deleteListOfProductsByIdList")
    public ResponseEntity<String> deleteListOfProducts(@RequestParam("idList") List<Long> idList) throws SystemException{
        try {
            productService.deleteListOfProductByIdList(idList);
            return ResponseEntity.ok("Products deleted successfully.");
        } catch (SystemException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @GetMapping("/productSearch")
    public ResponseEntity<List<ProductDto>> productSearch(@RequestParam("productSearch") String searchValue)   {
        return ResponseEntity.ok(productService.searchProductDto(searchValue));
    }

}
