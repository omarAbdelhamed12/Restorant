package com.spring.boot.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "`order`")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String code;
    @Column(nullable = false)
    private float totalPrice;
    @Column(nullable = false)
    private int totalNumber;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Product> products;

    @OneToMany(mappedBy = "order" ,cascade = CascadeType.ALL)
    List<User> users;


}
