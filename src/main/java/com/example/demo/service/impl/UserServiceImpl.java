package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;

import com.example.demo.db.repos.UserRepository;
import com.example.demo.db.repos.specifications.UserSpecification;
import com.example.demo.forms.customer.UserSearchForm;
import com.example.demo.models.UserEntity;
import com.example.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService{

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private UserSpecification userSpec;

  public Optional<List<UserEntity>> findBySearchSpec(UserSearchForm f) {
    return Optional.ofNullable(userRepository.findAll(
        Specifications.where(userSpec.loginIdContains(f.getLoginId()))
            .and(userSpec.emailContains(f.getEmail()))));
  }
}
