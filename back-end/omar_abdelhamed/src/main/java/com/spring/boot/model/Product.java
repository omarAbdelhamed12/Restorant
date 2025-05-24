package com.spring.boot.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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


}
