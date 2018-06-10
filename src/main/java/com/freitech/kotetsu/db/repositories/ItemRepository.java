package com.freitech.kotetsu.db.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.freitech.kotetsu.db.RepositoryBase;
import com.freitech.kotetsu.models.item.Item;

@Repository
public interface ItemRepository extends RepositoryBase<Item>,
  JpaSpecificationExecutor<Item> {
	Optional<Item> findById(Long Id);
}
