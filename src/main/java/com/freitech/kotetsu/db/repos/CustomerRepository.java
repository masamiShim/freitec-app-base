package com.freitech.kotetsu.db.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.freitech.kotetsu.models.customer.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{

}
