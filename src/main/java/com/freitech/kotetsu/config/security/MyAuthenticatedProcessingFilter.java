package com.freitech.kotetsu.config.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyAuthenticatedProcessingFilter extends  AbstractPreAuthenticatedProcessingFilter{

  @Override
  protected Object getPreAuthenticatedCredentials(HttpServletRequest arg0) {
    return "";
  }

  @Override
  protected Object getPreAuthenticatedPrincipal(HttpServletRequest httpServletRequest) {
    log.debug("X-TOKEN-AUTH: " + httpServletRequest.getHeader("X-TOKEN-AUTH"));
    String credentials = httpServletRequest.getHeader("X-TOKEN-AUTH");
    return credentials == null ? "" : credentials;
  }

}
