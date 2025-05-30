package com.spring.boot.controller;

import com.spring.boot.dto.CategoryDto;
import com.spring.boot.service.CategoryService;
import jakarta.transaction.SystemException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:4200")
public class CategoryController {

    @Autowired
     private CategoryService categoryService;

    @GetMapping("/getAll")
    public ResponseEntity<List<CategoryDto>> findAllCategoryDto() {
        return ResponseEntity.ok(categoryService.findAllCategoryDto());

    }

    @PostMapping("/creatCategory")
    public ResponseEntity<CategoryDto> createCategory(@RequestBody @Valid CategoryDto categoryDto) throws SystemException {
        return ResponseEntity.created(URI.create("/creatCategory")).body(categoryService.createCategory(categoryDto));
    }

    @PostMapping("/saveListOfCategory")
    public ResponseEntity<List<CategoryDto>> saveListOfCategory(@RequestBody @Valid  List<CategoryDto> categoryDtoList) throws SystemException {
        return ResponseEntity.created(URI.create("/saveListOfCategory")).body(categoryService.saveListOfCategoryDto(categoryDtoList));
    }
    @PutMapping("/updateCategory")
    public ResponseEntity<CategoryDto> updateCategory(@RequestBody @Valid CategoryDto categoryDto) throws SystemException {
        return ResponseEntity.ok(categoryService.updateCategory(categoryDto));
    }

    @PutMapping("/updateListOfCategory")
    public ResponseEntity<List<CategoryDto>> updateListOfCategory(@RequestBody @Valid List<CategoryDto> categoryDtoList) throws SystemException {
        return ResponseEntity.ok(categoryService.updateListOfCategory(categoryDtoList));
    }
    @DeleteMapping("/deleteCategory")
    public ResponseEntity<Void> deleteCategory(@RequestParam("id") Long id) throws SystemException {
        return categoryService.deleteCategoryById(id) ?
                ResponseEntity.noContent().build() :
                ResponseEntity.notFound().build();
    }
    @DeleteMapping("/deleteListOfCategory")
    public ResponseEntity<String> deleteListOfCategory(@RequestParam("idList") List<Long> idList) throws SystemException {
            try {
                categoryService.deleteListOfCategoryDtoByListOfId(idList);
                return ResponseEntity.ok("Products deleted successfully.");
            } catch (SystemException ex) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
            }
        }


}
