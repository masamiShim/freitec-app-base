package com.freitech.kotetsu.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.freitech.kotetsu.db.repos.UserRepository;
import com.freitech.kotetsu.forms.RegistrationForm;
import com.freitech.kotetsu.models.User;
import com.freitech.kotetsu.service.LoginService;
import com.freitech.kotetsu.service.RegistrationService;

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
  public User saveUser(RegistrationForm form) {
    log.debug(form.getLoginId());
    log.debug(form.getPassword());
    User user = new User(form.getLoginId(), loginService.encryptWithSalt(form.getPassword()));
    return userRepo.save(user);
  }

  @Override
  public boolean alreadyUsedLoginId(String loginId) {
    return userRepo.findByLoginId(loginId).isPresent();
  }
  
  

}
