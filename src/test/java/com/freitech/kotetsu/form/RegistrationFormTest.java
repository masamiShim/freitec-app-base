package com.freitech.kotetsu.form;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.freitech.kotetsu.AbstractTest;
import com.freitech.kotetsu.forms.RegistrationForm;

import lombok.extern.slf4j.Slf4j;;

@Slf4j
public class RegistrationFormTest extends AbstractTest{
  
  @Autowired
  private Validator v;

  @Before
  public void init(){
  }
  
  @Test
  public void validcheck(){
    log.info("ここまで来た");
    RegistrationForm form = new RegistrationForm();
    form.setConfirmation("");
    form.setPassword("");
    form.setLoginId("");
    Set<ConstraintViolation<RegistrationForm>> result = v.validate(form);
//    assertEquals(result.size(), 0);
  }
}
