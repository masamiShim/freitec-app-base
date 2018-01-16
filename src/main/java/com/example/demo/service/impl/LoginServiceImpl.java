package com.example.demo.service.impl;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.service.LoginService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LoginServiceImpl implements LoginService {
  @Value("login.password.salt")
  private String SALT;

  private static final int STRETCH_CNT = 10;
  private static final String SHA_256 = "SHA-256";

  @Override
  public String encryptWithSalt(String password) {
    // String hash = "";
    // for(int i = 0; i< STRETCH_CNT; i++){
    // hash = getSha256(hash.concat(SALT).concat(password));
    // }
    String hash = new BCryptPasswordEncoder().encode(password);
    return hash;
  }

  @Override
  public boolean isMatch(String password, String cryptedPass) {
    return cryptedPass.equals(encryptWithSalt(password));
  }

  /**
   * SHA256で暗号化された文字列を取得する
   * 
   * @param target
   * @return
   */
  private String getSha256(String target) {
    MessageDigest md = null;
    StringBuffer buf = new StringBuffer();
    try {
      md = MessageDigest.getInstance(SHA_256);
      md.update(target.getBytes());
      byte[] digest = md.digest();

      for (int i = 0; i < digest.length; i++) {
        buf.append(String.format("%02x", digest[i]));
      }
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
      log.error("そんな暗号化方法ないよ");
    }
    return buf.toString();
  }

}
