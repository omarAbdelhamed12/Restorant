package com.spring.boot.controller;

import com.spring.boot.controller.vm.ProductResponseVm;
import com.spring.boot.dto.ProductDto;
 import com.spring.boot.service.ProductService;
import jakarta.transaction.SystemException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/product")
@CrossOrigin("http://localhost:4200")
@Validated
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/getAll")
    public ResponseEntity<ProductResponseVm> findAllProductDto(@RequestParam("pageNumber")   int pageNumber,
                                                              @RequestParam("pageSize")      int pageSize) {
        return ResponseEntity.ok(productService.findAllProductDto(pageNumber,pageSize));
    }

    @GetMapping("/allProductByCategoryId/{categoryId}")
    public ResponseEntity<ProductResponseVm> allProductByCategoryId(@PathVariable Long categoryId,
                                                                   @RequestParam("pageNumber") int pageNumber,
                                                                   @RequestParam("pageSize")  int pageSize)  {
        return ResponseEntity.ok(productService.getProductsByCategoryId(categoryId,pageNumber,pageSize));
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
    public ResponseEntity<ProductResponseVm> productSearch(@RequestParam("productSearch") String searchValue ,
                                                           @RequestParam("pageNumber")   int pageNumber,
                                                           @RequestParam("pageSize")      int pageSize)   {
        return ResponseEntity.ok(productService.searchProductDto(searchValue,pageNumber,pageSize));
    }

    @GetMapping("/categorySearch")
    public ResponseEntity<ProductResponseVm> productSearch(
            @RequestParam("categoryId") Long categoryId,
            @RequestParam("searchValue") String searchValue,
            @RequestParam("pageNumber") int pageNumber,
            @RequestParam("pageSize") int pageSize)
    {
        return ResponseEntity.ok(productService.searchByCategoryIdAndKeyWord(categoryId, searchValue, pageNumber, pageSize));
    }


}
