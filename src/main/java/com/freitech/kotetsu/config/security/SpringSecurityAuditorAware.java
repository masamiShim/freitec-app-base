package com.freitech.kotetsu.config.security;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SpringSecurityAuditorAware implements AuditorAware<String> {
  @Override
  public String getCurrentAuditor() {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    if (null == auth || !auth.isAuthenticated()) {
      return null;
    }
    return auth.getName();
  }

}
