package com.example.demo.forms;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.ToString;

@Data
@Component
@ToString
public class RegistrationForm implements FormBase {

  @NotBlank(message="{loginId.required}")
  @Size(min = 6, max = 255, message="{loginId.range}")
  private String loginId;

  @NotBlank(message="{password.required}")
  @Size(min = 8, max = 20, message="{password.range}")
  private String password;

  @NotBlank
  private String confirmation;
}
