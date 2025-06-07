package com.spring.boot.repo;

import com.spring.boot.model.Chef;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChefRepo extends JpaRepository<Chef, Integer> {
}
