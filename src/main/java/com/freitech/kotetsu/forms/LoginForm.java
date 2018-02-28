package com.freitech.kotetsu.forms;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class LoginForm implements FormBase {

  @NotEmpty(message="{loginId.required}")
  @Length(min = 6, max = 255, message="{loginId.range}")
  private String loginId;
  
  @NotEmpty(message="{password.required}")
  @Size(min = 8, max = 20, message="{password.range}")
  private String password;
  
}
