package com.example.demo.db.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Staff;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long>{

}
