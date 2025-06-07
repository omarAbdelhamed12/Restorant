package com.spring.boot.model;

import com.spring.boot.Enum.RoleCode;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private RoleCode code;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<User> users;

}
