package com.freitech.kotetsu.db.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.freitech.kotetsu.models.estimate.Estimate;

@Repository
public interface EstimateRepository
  extends JpaRepository<Estimate, Long>, JpaSpecificationExecutor<Estimate> {

	Optional<Estimate> findById(Long id);
}
