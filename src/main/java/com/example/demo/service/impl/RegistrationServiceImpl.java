package com.example.demo.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.db.repos.UserRepository;
import com.example.demo.forms.RegistrationForm;
import com.example.demo.models.UserEntity;
import com.example.demo.service.LoginService;
import com.example.demo.service.RegistrationService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RegistrationServiceImpl implements RegistrationService {

  @Autowired
  LoginService loginService;

  @Autowired
  UserRepository userRepo;

  @Override
  @Transactional
  public UserEntity saveUser(RegistrationForm form) {
    log.debug(form.getLoginId());
    log.debug(form.getPassword());
    UserEntity user = new UserEntity(form.getLoginId(), loginService.encryptWithSalt(form.getPassword()));
    return userRepo.save(user);
  }

  @Override
  public boolean alreadyUsedLoginId(String loginId) {
    return userRepo.findByLoginId(loginId).isPresent();
  }
  
  

}
