package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.forms.customer.UserSearchForm;
import com.example.demo.models.UserEntity;

public interface UserService {
  /**
   * 検索条件からユーザを取得する。
   * 
   * @param f
   * @return
   */
  Optional<List<UserEntity>> findBySearchSpec(UserSearchForm f);
}
