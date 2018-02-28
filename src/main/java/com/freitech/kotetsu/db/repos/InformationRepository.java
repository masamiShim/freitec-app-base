package com.freitech.kotetsu.db.repos;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.freitech.kotetsu.models.Information;

@Repository
public interface InformationRepository
    extends JpaRepository<Information, Long> {
/*
	@Query(value = "SELECT e FROM Information e "
      + " WHERE :dispDate BETWEEN e.startDate AND e.endDate "
      + " AND e.deleted = false",nativeQuery = false)
  List<Information> findByDateFromToAndDeletedFalse(
      @Param(value = "dispDate") LocalDate dispDate);
  */
    Optional<List<Information>> findByDeletedFalse();
}
