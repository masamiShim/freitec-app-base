package com.example.demo.db.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.CustomerAttribute;

public interface CustomerAttributeRepository extends JpaRepository<CustomerAttribute, Long>{

}
