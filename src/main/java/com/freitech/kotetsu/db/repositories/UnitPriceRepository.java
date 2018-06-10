package com.freitech.kotetsu.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.freitech.kotetsu.db.RepositoryBase;
import com.freitech.kotetsu.models.UnitPrice;

@Repository
public interface UnitPriceRepository extends RepositoryBase<UnitPrice> {

}
