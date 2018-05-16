package com.freitech.kotetsu.db.repos;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.freitech.kotetsu.models.information.Information;

@Repository
public interface InformationRepository extends JpaRepository<Information, Long>,
  JpaSpecificationExecutor<Information> {

	// @formatter:off
	@Query("SELECT e FROM Information e " 
			+ " WHERE :startDate >= e.startDate "
			+ " AND :endDate <= e.endDate " 
			+ " AND e.deleted IS NULL ")
	// @formatter:on
	Optional<List<Information>> findByDateFromToAndDeletedNull(
	                                                           @Param("startDate") LocalDate startDate,
	                                                           @Param("endDate") LocalDate endDate);

	// @formatter:off
	@Query("SELECT e FROM Information e " 
			+ " WHERE :dispDate BETWEEN e.startDate AND e.endDate "
			+ " AND e.deleted IS NULL ")
	// @formatter:on
	Optional<List<Information>> findByCurrentDisplayInfo(@Param("dispDate") LocalDate dispDate);

	Optional<Information> findById(Long id);
}
