package com.example.demo.service;

public interface LoginService {
 
 /**
  * パスワードを暗号化する 
  * @param password
  * @return
  */
 public String encryptWithSalt(String password);

 /**
  * パスワードが登録されたパスワードと一致するか確認する 
  * @param password
  * @param cryptPass 暗号化されたパスワード
  * @return
  */
 public boolean isMatch(String password, String cryptPass);
 
}
