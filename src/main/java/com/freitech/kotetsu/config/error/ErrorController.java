package com.freitech.kotetsu.config.error;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class ErrorController {

  @GetMapping(value = "/403")
  @ResponseStatus(value = HttpStatus.FORBIDDEN)
  public String forbidden() {
    return "error/403";
  }
  
  @GetMapping(value = "/404")
  @ResponseStatus(value = HttpStatus.NOT_FOUND)
  public String notFound() {
    return "error/404";
  }

  @GetMapping(value = "/405")
  @ResponseStatus(value = HttpStatus.NOT_FOUND)
  public String unSupportedMethod() {
    return "error/404";
  }

  @GetMapping(value = "/500")
  @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
  public String internalServerError() {
    return "error/500";
  }
}
