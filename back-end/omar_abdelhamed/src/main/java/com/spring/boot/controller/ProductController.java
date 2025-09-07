package com.spring.boot.controller;

import com.spring.boot.controller.vm.ProductResponseVm;
import com.spring.boot.dto.ExceptionDto;
import com.spring.boot.dto.ProductDto;
import com.spring.boot.exception.CustomSystemException;
import com.spring.boot.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.SystemException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Tag(

        name = "Product Controller",
        description = "APIs to create ,insert, delete and update "
)
@RestController
@RequestMapping("/api/product")
//@CrossOrigin("http://localhost:4200")
@Validated
public class ProductController {
    @Autowired
    private ProductService productService;

    @Operation(
            summary = "getAll products API",
            description = "this API to get all product."
    )
    @ApiResponses( value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status get all product "
            ),
             @ApiResponse(
                    responseCode = "500",
                    description = "HTTP  Internal Server Error ",
                    content = @Content(
                            schema = @Schema(implementation = ExceptionDto.class)
                    )
            )
    } )
    @GetMapping("/getAll")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<ProductResponseVm> findAllProductDto(@RequestParam("pageNumber")   int pageNumber,
                                                              @RequestParam("pageSize")      int pageSize) {
        return ResponseEntity.ok(productService.findAllProductDto(pageNumber,pageSize));
    }

    @GetMapping("/allProductByCategoryId/{categoryId}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<ProductResponseVm> allProductByCategoryId(@PathVariable Long categoryId,
                                                                   @RequestParam("pageNumber") int pageNumber,
                                                                   @RequestParam("pageSize")  int pageSize)  {
        return ResponseEntity.ok(productService.getProductsByCategoryId(categoryId,pageNumber,pageSize));
    }
    @PostMapping("/creatProduct")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<ProductDto> createProduct(@RequestBody @Valid ProductDto productDto)   {
        return ResponseEntity.created(URI.create("/creatProduct")).body(productService.createProduct(productDto));
    }
    @PostMapping("/saveListOfProducts")
    public ResponseEntity<List<ProductDto>> saveListOfProducts(@RequestBody @Valid List<ProductDto> productDtoList)  {
        return ResponseEntity.created(URI.create("/saveListOfProducts")).body(productService.saveListOfProduct(productDtoList));
    }
    @PutMapping("/updateProduct")
    @PreAuthorize("hasAnyRole(  'ADMIN')")
    public ResponseEntity<ProductDto> updateProduct(@RequestBody @Valid ProductDto productDto)   {
        return ResponseEntity.ok(productService.updateProduct(productDto));
    }
    @PutMapping("/updateListOfProducts")
    public ResponseEntity<List<ProductDto>> updateListOfProducts(@RequestBody @Valid List<ProductDto> productDtoList)  {
        return ResponseEntity.ok(productService.updateListOfProduct(productDtoList));
    }
    @DeleteMapping("/deleteProduct")
    @PreAuthorize("hasAnyRole('ADMIN')")
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
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<ProductResponseVm> productSearch(@RequestParam("productSearch") String searchValue ,
                                                           @RequestParam("pageNumber")   int pageNumber,
                                                           @RequestParam("pageSize")      int pageSize)   {
        return ResponseEntity.ok(productService.searchProductDto(searchValue,pageNumber,pageSize));
    }

    @GetMapping("/categorySearch")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<ProductResponseVm> productSearch(
            @RequestParam("categoryId") Long categoryId,
            @RequestParam("searchValue") String searchValue,
            @RequestParam("pageNumber") int pageNumber,
            @RequestParam("pageSize") int pageSize)
    {
        return ResponseEntity.ok(productService.searchByCategoryIdAndKeyWord(categoryId, searchValue, pageNumber, pageSize));
    }


}
