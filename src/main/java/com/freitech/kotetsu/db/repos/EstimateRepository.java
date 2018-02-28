package com.freitech.kotetsu.db.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.freitech.kotetsu.models.Estimate;

@Repository
public interface EstimateRepository
    extends JpaRepository<Estimate, Long>, JpaSpecificationExecutor<Estimate> {
}
