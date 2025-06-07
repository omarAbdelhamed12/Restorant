package com.spring.boot.controller;

import com.spring.boot.dto.ChefDto;
import com.spring.boot.service.ChefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/chef")
@CrossOrigin("http://localhost:4200")
public class ChefController {

    @Autowired
    private ChefService chefService;

    @GetMapping("/getAllChef")
    public ResponseEntity<List<ChefDto>> getAllChefs() {
        return ResponseEntity.ok(chefService.getALLChefs());
    }
}
