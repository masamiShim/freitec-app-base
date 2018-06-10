package com.freitech.kotetsu.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.freitech.kotetsu.models.customer.CustomerAttribute;

public interface CustomerAttributeRepository extends JpaRepository<CustomerAttribute, Long>{

}
