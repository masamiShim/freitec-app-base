package com.example.demo.forms.customer;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class UserSearchForm {

  @Size(max = 255, message="{loginId.maxlen}")
  private String loginId;

  @Length(max = 255, message="{email.maxlen}")
  private String email;

}
