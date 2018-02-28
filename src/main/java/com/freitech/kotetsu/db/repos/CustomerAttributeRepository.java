package com.freitech.kotetsu.db.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.freitech.kotetsu.models.CustomerAttribute;

public interface CustomerAttributeRepository extends JpaRepository<CustomerAttribute, Long>{

}
