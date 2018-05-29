package com.freitech.kotetsu.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.freitech.kotetsu.models.Staff;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long>{

}
