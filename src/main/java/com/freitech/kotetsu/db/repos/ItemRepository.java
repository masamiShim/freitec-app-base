package com.freitech.kotetsu.db.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.freitech.kotetsu.models.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long>{

}
