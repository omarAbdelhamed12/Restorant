package com.spring.boot.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ProductDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String ingredients;     // المكونات

    private int calories;           // السعرات الحرارية
    private int preparationTime;   // وقت التحضير بالدقايق

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

}
