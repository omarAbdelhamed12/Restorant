package com.spring.boot.service.impl;

import com.spring.boot.dto.ProductDetailsDto;
import com.spring.boot.dto.ProductDto;
import com.spring.boot.exception.CustomSystemException;
import com.spring.boot.model.Product;
import com.spring.boot.model.ProductDetails;
import com.spring.boot.modelMapper.ProductDetailsMapper;
import com.spring.boot.repo.ProductDetailsRepo;
import com.spring.boot.repo.ProductRepo;
import com.spring.boot.service.ProductDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class ProductDetailsServiceImpl implements ProductDetailsService {
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    ProductDetailsRepo productDetailsRepo;

    @Override
    public ProductDetailsDto addProductDetails(ProductDetailsDto productDetailsDto) {
        Optional<Product> product = productRepo.findById(productDetailsDto.getProductId());
        if (!product.isPresent()) {
            throw new CustomSystemException("Product not found");
        }
        if (product.get().getProductDetails() != null){
            ProductDetails productDetails = product.get().getProductDetails();
            productDetails.setCalories(productDetailsDto.getCalories());
            productDetails.setIngredients(productDetailsDto.getIngredients());
            productDetails.setPreparationTime(productDetailsDto.getPreparationTime());
            ProductDetails update = productDetailsRepo.save(productDetails);
            return ProductDetailsMapper.PRODUCT_DETAILS_MAPPER.toProductDetailsDto(update);
        }
        ProductDetails details = ProductDetailsMapper.PRODUCT_DETAILS_MAPPER.toProductDetails(productDetailsDto);
        details.setProduct(product.get());
        productDetailsRepo.save(details);
        return productDetailsDto;
    }

    @Override
    public ProductDetailsDto getProductDetailsByProductId(Long productId) {
        Optional<ProductDetails> productDetails = productDetailsRepo.findProductDetailsByProduct_Id(productId);
        if (!productDetails.isPresent()) {
            throw new CustomSystemException("no product details found");
        }
        ProductDetailsDto details = ProductDetailsMapper.PRODUCT_DETAILS_MAPPER.toProductDetailsDto(productDetails.get()) ;

        return details ;
    }
}
