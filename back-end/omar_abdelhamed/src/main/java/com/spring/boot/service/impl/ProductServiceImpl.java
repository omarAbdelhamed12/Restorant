package com.spring.boot.service.impl;

import com.spring.boot.controller.vm.ProductResponseVm;
import com.spring.boot.dto.CategoryDto;
import com.spring.boot.dto.ProductDto;
import com.spring.boot.exception.CustomSystemException;
import com.spring.boot.model.Category;
import com.spring.boot.model.Product;
import com.spring.boot.modelMapper.ProductMapper;
import com.spring.boot.repo.CategoryRepo;
import com.spring.boot.repo.ProductRepo;
import com.spring.boot.service.ProductService;
import jakarta.validation.constraints.Max;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class ProductServiceImpl  implements ProductService {

    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private CategoryRepo categoryRepo;
   // @Autowired
    //private ProductMapper productMapper;


    @Override
    public ProductResponseVm findAllProductDto(int pageNumber, int pageSize) {
        if ( Objects.isNull(pageNumber) || pageNumber < 1) {
            throw new CustomSystemException("number.of.page");
        }
        if (Objects.isNull(pageSize) || pageSize < 1) {
            throw new CustomSystemException("size.of.page");
        }
        Pageable pageable = PageRequest.of(pageNumber - 1 , pageSize);
       Page<Product> products = productRepo.findAllByOrderByIdAsc(pageable);

       return new ProductResponseVm(products.getContent().stream().map(ProductMapper.PRODUCT_MAPPER::toProductDto).toList(),
               products.getTotalElements());
//        return getProductDtos(products.getContent());
    }

    @Override
    public ProductResponseVm getProductsByCategoryId(Long categoryId, int pageNumber, int pageSize) {

        if(Objects.isNull(categoryId)){
            throw new CustomSystemException("error.id.invalid");
        }
        Optional<Category>category = categoryRepo.findById(categoryId);
         if(category.isEmpty()){
             throw new CustomSystemException("error.categoryName.unfound");
         }
        if ( Objects.isNull(pageNumber) || pageNumber < 1) {
            throw new CustomSystemException("number.of.page");
        }
        if (Objects.isNull(pageSize) || pageSize < 1) {
            throw new CustomSystemException("size.of.page");
        }
        Pageable pageable = PageRequest.of(pageNumber - 1 , pageSize);
        Page<Product> products = productRepo.findProductsByCategoryId(categoryId ,pageable);
        return new ProductResponseVm(products.getContent().stream().map(ProductMapper.PRODUCT_MAPPER::toProductDto).toList(),
                products.getTotalElements());
//        return getProductDtos(products);
    }

    @Override
    public ProductDto createProduct(ProductDto productDto){
        if(Objects.nonNull(productDto.getId())){
            throw new CustomSystemException("error.idProduct.invalid");
        }
         Optional<Product> product = productRepo.findByName(productDto.getName());
        if(product.isPresent()){
            throw new CustomSystemException("error.productName.found");
        }
        Product product1 = ProductMapper.PRODUCT_MAPPER.toProduct(productDto);
        productRepo.save(product1);
        return productDto;
    }

    @Override
    public List<ProductDto> saveListOfProduct(List<ProductDto> productDtoList){
        if(Objects.nonNull(productDtoList.get(0).getId())){
            throw new CustomSystemException("error.idProduct.invalid");
        }
        Optional<Product> product = productRepo.findByName(productDtoList.get(0).getName());
        if(product.isPresent()){
            throw new ClassCastException("error.productName.unfound");
        }
        Product product1 = ProductMapper.PRODUCT_MAPPER.toProduct(productDtoList.get(0));
        productRepo.save(product1);

        return productDtoList;
    }

    @Override
    public ProductDto updateProduct(ProductDto productDto){
        if(Objects.isNull(productDto.getId())){
            throw new CustomSystemException("error.idProduct.valid");
        }
        Optional<Product> product = productRepo.findById(productDto.getId());
        if(product.isEmpty()){
            throw new CustomSystemException("error.product.notfound");
        }
        Product product1 = ProductMapper.PRODUCT_MAPPER.toProduct(productDto);
        productRepo.save(product1);
        return productDto;
    }

    @Override
    public List<ProductDto> updateListOfProduct(List<ProductDto> productDtoList){
        List<ProductDto> updateList=new ArrayList<>();
        for (ProductDto productDto : productDtoList) {
        if (Objects.isNull(productDto.getId())){
            throw new CustomSystemException("error.idProduct.invalid");
        }
        Optional<Product> product = productRepo.findById(productDto.getId());
        if (product.isEmpty()) {
            throw new CustomSystemException("error.productName.unfound");
        }

        Product product1 = ProductMapper.PRODUCT_MAPPER.toProduct(productDto);
        productRepo.save(product1);
        updateList.add(productDto);
        }
        return updateList;
    }

    @Override
    public boolean deleteProductById(Long id){
        Optional<Product> product = productRepo.findById(id);
        if (product.isEmpty()) {
            return false;
        }
        productRepo.deleteById(id);
        return true;
    }

    @Override
    public boolean deleteListOfProductByIdList(List<Long> idList){
        List<Long> notFoundIds = new ArrayList<>();

        for (Long id : idList) {
            if (productRepo.existsById(id)) {
                productRepo.deleteById(id);
            } else {
                notFoundIds.add(id);
            }
        }

        if (!notFoundIds.isEmpty()) {
            throw new CustomSystemException("error.productName.unfound");
        }

        return true;
    }

    @Override
    public ProductResponseVm searchProductDto(String searchValue , int pageNumber, int pageSize){

        if(Objects.isNull(searchValue) || searchValue.trim().isEmpty()) {
            throw new CustomSystemException("error.searchValue.found");
        }
        if ( Objects.isNull(pageNumber) || pageNumber < 1) {
            throw new CustomSystemException("number.of.page");
        }
        if (Objects.isNull(pageSize) || pageSize < 1) {
            throw new CustomSystemException("size.of.page");
        }
        Pageable pageable = PageRequest.of(pageNumber - 1 , pageSize);
        Page<Product> product = productRepo.
                findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(searchValue,searchValue,pageable);
        if (product.isEmpty()) {
            throw new CustomSystemException("error.searchValue.found");
        }

        return new ProductResponseVm(product.getContent().stream().map(ProductMapper.PRODUCT_MAPPER::toProductDto).toList(),
                product.getTotalElements());
    }

    @Override
    public ProductResponseVm searchByCategoryIdAndKeyWord(Long categoryId, String searchValue, int pageNumber, int pageSize) {
        if ( Objects.isNull(pageNumber) || pageNumber < 1) {
            throw new CustomSystemException("number.of.page");
        }
        if (Objects.isNull(pageSize) || pageSize < 1) {
            throw new CustomSystemException("size.of.page");
        }
        Pageable pageable = PageRequest.of(pageNumber - 1 , pageSize);
        Page<Product> product = productRepo.
                findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCaseAndCategoryId(searchValue,searchValue,categoryId,pageable);
        return new ProductResponseVm(product.getContent().stream().map(ProductMapper.PRODUCT_MAPPER::toProductDto).toList(),
                product.getTotalElements());
    }


//    private static List<ProductDto> getProductDtos(List<Product> product){
//        List<ProductDto> productDtos=new ArrayList<>();
//        for (Product product1 : product) {
//            ProductDto productDto = ProductMapper.PRODUCT_MAPPER.toProductDto(product1);
//            productDto.setCategory(null);
//            productDtos.add(productDto);
//        }
//        return productDtos;
//    }


}

