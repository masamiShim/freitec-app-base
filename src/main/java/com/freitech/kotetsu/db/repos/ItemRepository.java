package com.freitech.kotetsu.db.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.freitech.kotetsu.models.item.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long>,
  JpaSpecificationExecutor<Item> {
	Optional<Item> findById(Long Id);
}
