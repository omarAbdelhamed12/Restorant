package com.spring.boot.service.impl;

import com.spring.boot.dto.CategoryDto;
import com.spring.boot.dto.ProductDto;
import com.spring.boot.model.Category;
import com.spring.boot.model.Product;
import com.spring.boot.modelMapper.ProductMapper;
import com.spring.boot.repo.CategoryRepo;
import com.spring.boot.repo.ProductRepo;
import com.spring.boot.service.ProductService;
import jakarta.transaction.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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



    @Override
    public List<ProductDto> getProductsByCategoryId(Long categoryId) throws SystemException {

        if(Objects.isNull(categoryId)){
            throw new SystemException("error.id.invalid");
        }
        Optional<Category>category = categoryRepo.findById(categoryId);
         if(category.isEmpty()){
             throw new SystemException("error.categoryName.unfound");
         }
        List<Product> products = productRepo.findProductsByCategoryId(categoryId);
        List<ProductDto> productDtos=new ArrayList<>();
        for (Product product : products) {
            ProductDto productDto = ProductMapper.PRODUCT_MAPPER.toProductDto(product);
            productDto.setCategory(null);
            productDtos.add(productDto);
        }
        return productDtos;
    }

    @Override
    public ProductDto createProduct(ProductDto productDto) throws SystemException {
        if(Objects.nonNull(productDto.getId())){
            throw new SystemException("error.idProduct.invalid");
        }
         Optional<Product> product = productRepo.findByName(productDto.getName());
        if(product.isPresent()){
            throw new SystemException("error.productName.found");
        }
        Product product1 = ProductMapper.PRODUCT_MAPPER.toProduct(productDto);
        productRepo.save(product1);
        return productDto;
    }

    @Override
    public List<ProductDto> saveListOfProduct(List<ProductDto> productDtoList) throws SystemException {
        if(Objects.nonNull(productDtoList.get(0).getId())){
            throw new SystemException("error.idProduct.invalid");
        }
        Optional<Product> product = productRepo.findByName(productDtoList.get(0).getName());
        if(product.isPresent()){
            throw new SystemException("error.productName.unfound");
        }
        Product product1 = ProductMapper.PRODUCT_MAPPER.toProduct(productDtoList.get(0));
        productRepo.save(product1);

        return productDtoList;
    }

    @Override
    public ProductDto updateProduct(ProductDto productDto) throws SystemException {
        if(Objects.isNull(productDto.getId())){
            throw new SystemException("error.idProduct.valid");
        }
        Optional<Product> product = productRepo.findById(productDto.getId());
        if(product.isEmpty()){
            throw new SystemException("error.product.notfound");
        }
        Product product1 = ProductMapper.PRODUCT_MAPPER.toProduct(productDto);
        productRepo.save(product1);
        return productDto;
    }

    @Override
    public List<ProductDto> updateListOfProduct(List<ProductDto> productDtoList) throws SystemException {
        List<ProductDto> updateList=new ArrayList<>();
        for (ProductDto productDto : productDtoList) {
        if (Objects.isNull(productDto.getId())){
            throw new SystemException("error.idProduct.invalid");
        }
        Optional<Product> product = productRepo.findById(productDto.getId());
        if (product.isEmpty()) {
            throw new SystemException( "error.productName.unfound");
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
    public boolean deleteListOfProductByIdList(List<Long> idList) throws SystemException {
        List<Long> notFoundIds = new ArrayList<>();

        for (Long id : idList) {
            if (productRepo.existsById(id)) {
                productRepo.deleteById(id);
            } else {
                notFoundIds.add(id);
            }
        }

        if (!notFoundIds.isEmpty()) {
            throw new SystemException("error.productName.unfound");
        }

        return true;
    }


}

