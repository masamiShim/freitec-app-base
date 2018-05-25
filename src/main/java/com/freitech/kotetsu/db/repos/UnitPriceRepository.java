package com.freitech.kotetsu.db.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.freitech.kotetsu.models.UnitPrice;

@Repository
public interface UnitPriceRepository extends JpaRepository<UnitPrice, Long>,
  JpaSpecificationExecutor<UnitPrice> {
	Optional<UnitPrice> findById(Long Id);

}
