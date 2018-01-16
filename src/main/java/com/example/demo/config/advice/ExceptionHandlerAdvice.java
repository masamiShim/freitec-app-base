package com.example.demo.config.advice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.example.demo.exceptions.OptimisticLockWrappedException;

@ControllerAdvice
public class ExceptionHandlerAdvice {

  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  @ExceptionHandler({ OptimisticLockWrappedException.class })
  @ResponseBody
  public Map<String, Object> handleError() {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("message", "許可されていないメソッド");
    return map;
  }
}
