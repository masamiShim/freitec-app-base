package com.freitech.kotetsu.db.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.freitech.kotetsu.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, String>,
    JpaSpecificationExecutor<User> {

  Optional<User> findByLoginId(String userId);

  Optional<User> findByLoginIdAndPassword(String loginId,
      String password);
/*
  @Query(value=" SELECT "
      + "   e.userId, e.loginId"
      + "   , e.name, e.email"
      + "   , e.locked, e.accountExpired"
      + " FROM User e"
      + " WHERE e.deleted = false "
      + " ORDER BY e.userId DESC")
      */
  //UserListCsv readUserCsvList();

}
