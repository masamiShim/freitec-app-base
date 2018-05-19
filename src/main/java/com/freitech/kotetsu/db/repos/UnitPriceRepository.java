package com.freitech.kotetsu.db.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.freitech.kotetsu.models.UnitPrice;

@Repository
public interface UnitPriceRepository extends JpaRepository<UnitPrice, Long>{

}