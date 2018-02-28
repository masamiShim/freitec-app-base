package com.freitech.kotetsu.service;

import com.freitech.kotetsu.forms.RegistrationForm;
import com.freitech.kotetsu.models.User;

public interface RegistrationService {

  /**
   * ユーザを登録する。
   * @param form
   * @return
   */
  public User saveUser(RegistrationForm form);

  /**
   * ログインIDがすでに利用されているか確認する
   * @param loginId
   * @return
   */
  public boolean alreadyUsedLoginId(String loginId);

}
