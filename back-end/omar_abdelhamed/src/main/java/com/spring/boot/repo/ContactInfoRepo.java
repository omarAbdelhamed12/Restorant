package com.spring.boot.repo;

import com.spring.boot.model.ContactInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactInfoRepo extends JpaRepository<ContactInfo, Long> {
}
