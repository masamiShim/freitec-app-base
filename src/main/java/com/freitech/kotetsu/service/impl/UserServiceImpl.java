package com.freitech.kotetsu.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;

import com.freitech.kotetsu.db.repos.UserRepository;
import com.freitech.kotetsu.db.repos.specifications.UserSpecification;
import com.freitech.kotetsu.forms.customer.UserSearchForm;
import com.freitech.kotetsu.models.User;
import com.freitech.kotetsu.service.UserService;

@Service
public class UserServiceImpl implements UserService{

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private UserSpecification userSpec;

  public Optional<List<User>> findBySearchSpec(UserSearchForm f) {
    return Optional.ofNullable(userRepository.findAll(
        Specifications.where(userSpec.loginIdContains(f.getLoginId()))
            .and(userSpec.emailContains(f.getEmail()))));
  }
}
