package com.example.demo.db.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.models.UserEntity;
import com.example.demo.models.csv.UserListCsv;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String>,
    JpaSpecificationExecutor<UserEntity> {

  Optional<UserEntity> findByLoginId(String userId);

  Optional<UserEntity> findByLoginIdAndPassword(String loginId,
      String password);

  @Query(value=" SELECT "
      + "   e.userId, e.loginId"
      + "       , e.name, e.email"
      + "   ,    e.locked, e.accountExpired"
      + " FROM UserEntity e"
      + " WHERE e.deleted = false "
      + " ORDER BY e.userId DESC")
  Optional<UserListCsv> readUserCsvList();

}
