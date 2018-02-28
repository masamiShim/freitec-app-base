package com.freitech.kotetsu.models.csv;

import com.github.mygreen.supercsv.annotation.CsvBean;
import com.github.mygreen.supercsv.annotation.CsvColumn;

import lombok.Data;

@Data
@CsvBean
public class UserListCsv {

  @CsvColumn(number = 0, label = "ユーザID")
  private String userId;

  @CsvColumn(number = 1, label = "ログインID")
  private String loginId;

  @CsvColumn(number = 2, label = "ユーザ名")
  private String name;

  @CsvColumn(number = 3, label = "メールアドレス")
  private String email;

  @CsvColumn(number = 4, label = "停止中")
  private String locked;

  @CsvColumn(number = 5, label = "失効済み")
  private String accountExpired;

}
