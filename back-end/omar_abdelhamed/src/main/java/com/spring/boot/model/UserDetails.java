package com.spring.boot.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Long age;
    @Column(nullable = false)
    private String phoneNumber;
    @Column(nullable = false)
    private String address;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

}
