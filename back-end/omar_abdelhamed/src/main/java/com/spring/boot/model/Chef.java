package com.spring.boot.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Chef {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String specialty;

    private String logoPath;

    @Column(name = "FACEBOOK_LINK")
    private String faceLink;

    @Column(name = "TWITTER_LINK")
    private String tweLink;
    @Column(name = "INSTAGRAM_LINK")
    private String instaLink;
}
