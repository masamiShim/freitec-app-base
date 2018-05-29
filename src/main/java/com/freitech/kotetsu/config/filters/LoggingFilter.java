package com.freitech.kotetsu.config.filters;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class LoggingFilter implements Filter {

  @Override
  public void doFilter(ServletRequest request, ServletResponse response,
      FilterChain chain) throws IOException, ServletException {
	  
    HttpServletRequest req = ((HttpServletRequest) request);
    String uri = req.getRequestURI();

    // 許可分はスルー
    if (this.isAllowsAccess(uri)) {
      chain.doFilter(request, response);
      return;
    }

    long start = System.currentTimeMillis();
    String identifier = String.format("[%s] : [%s] -> ", req.getRemoteUser(),
        req.getRequestedSessionId());
    
    log.info(String.format(" %s start ", identifier, start));
    log.info(String.format(" %s URI: %s", identifier, uri));

    // パラメータ出力
    Map<String, String[]> params = req.getParameterMap();
//    params.entrySet().stream().forEach(e -> {
//      log.info(String.format("%s PARAM_KEY: %s, PARAM_VALUE: %s", identifier,
//          e.getKey(), Arrays.toString(e.getValue())));
//    });
    
    // 処理実行
    chain.doFilter(request, response);

    // 処理時間とステータスを記録
//    log.info(String.format(" %s end in %d millisec. STATUS[%d]", identifier,
//        System.currentTimeMillis() - start,
//        ((HttpServletResponse) response).getStatus()));
  }

  @Override
  public void init(FilterConfig arg0) throws ServletException {
  }

  @Override
  public void destroy() {

  }

  private boolean isAllowsAccess(String uri) {
    return uri.contains("/js/") 
        || uri.contains("/css/")
        || uri.contains("/fonts/") 
        || uri.contains("/img/")
        || uri.contains("/favicon")
        || uri.contains("/webjars/");
  }

}
