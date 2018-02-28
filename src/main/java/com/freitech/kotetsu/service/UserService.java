package com.freitech.kotetsu.service;

import java.util.List;
import java.util.Optional;

import com.freitech.kotetsu.forms.customer.UserSearchForm;
import com.freitech.kotetsu.models.User;

public interface UserService {
  /**
   * 検索条件からユーザを取得する。
   * 
   * @param f
   * @return
   */
  Optional<List<User>> findBySearchSpec(UserSearchForm f);
}
