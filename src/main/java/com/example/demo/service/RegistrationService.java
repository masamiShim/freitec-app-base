package com.example.demo.service;

import com.example.demo.forms.RegistrationForm;
import com.example.demo.models.UserEntity;

public interface RegistrationService {

  /**
   * ユーザを登録する。
   * @param form
   * @return
   */
  public UserEntity saveUser(RegistrationForm form);

  /**
   * ログインIDがすでに利用されているか確認する
   * @param loginId
   * @return
   */
  public boolean alreadyUsedLoginId(String loginId);

}
