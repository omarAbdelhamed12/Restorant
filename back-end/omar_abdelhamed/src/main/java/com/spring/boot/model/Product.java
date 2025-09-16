package com.spring.boot.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String imagePath;

    private String description;

    @Column(nullable = false)
    private float price;

    @ManyToOne
    @JoinColumn(nullable = false,name = "category_id")
    private Category category;

    @ManyToMany(mappedBy = "products",fetch = FetchType.EAGER)
    private List<Order> orders;

    @OneToOne(mappedBy = "product" , cascade = CascadeType.ALL)
    private ProductDetails productDetails;

}
