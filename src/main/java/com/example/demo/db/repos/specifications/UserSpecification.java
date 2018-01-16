package com.example.demo.db.repos.specifications;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.example.demo.models.UserEntity;

@Component
public class UserSpecification extends SpecificationBase{
  public Specification<UserEntity> loginIdContains(String loginId){
    return StringUtils.isEmpty(loginId) ? null : (root, query, cb) -> {
      return cb.like(root.get("loginId"), castPartLikeString(loginId));
    };
  }

  public Specification<UserEntity> emailContains(String email){
    return StringUtils.isEmpty(email) ? null : (root, query, cb) -> {
      return cb.like(root.get("email"), castPartLikeString(email));
    };
  }

}
