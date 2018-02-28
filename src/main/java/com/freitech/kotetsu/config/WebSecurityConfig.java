package com.freitech.kotetsu.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.security.web.csrf.MissingCsrfTokenException;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.freitech.kotetsu.config.security.SessionExpiredDetectingLoginUrlAuthenticationEntryPoint;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private UserDetailsService userDetailService;

  @Autowired
  private AuthenticationProvider authProvider;

  // @Bean
  // public
  // AuthenticationUserDetailsService<PreAuthenticatedAuthenticationToken>
  // authenticationUserDetailsService() {
  // return new UserDetailsServiceImpl();
  // }

  // @Bean
  // public PreAuthenticatedAuthenticationProvider
  // preAuthenticatedAuthenticationProvider() {
  // PreAuthenticatedAuthenticationProvider provider = new
  // PreAuthenticatedAuthenticationProvider();
  // provider.setPreAuthenticatedUserDetailsService(authenticationUserDetailsService());
  // provider.setUserDetailsChecker(new AccountStatusUserDetailsChecker());
  // return provider;
  // }

  // @Bean
  // public AbstractPreAuthenticatedProcessingFilter
  // preAuthenticatedProcessingFilter() throws Exception {
  // MyAuthenticatedProcessingFilter filter = new
  // MyAuthenticatedProcessingFilter();
  // filter.setAuthenticationManager(authenticationManager());
  // return filter;
  // }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailService)
        .passwordEncoder(new BCryptPasswordEncoder())
    // .authenticationProvider(authProvider)

    ;

  }

  @Bean
  AccessDeniedHandler accessDeniedHandler() {
    return new AccessDeniedHandler() {
      @Override
      public void handle(HttpServletRequest request,
          HttpServletResponse response,
          AccessDeniedException accessDeniedException)
          throws IOException, ServletException {
        if (accessDeniedException instanceof MissingCsrfTokenException) {
          authenticationEntryPoint().commence(request, response, null);
        } else {
          new AccessDeniedHandlerImpl().handle(request, response,
              accessDeniedException);
        }
      }
    };
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.headers().xssProtection().and().frameOptions().and()
        .contentTypeOptions().and().cacheControl();
// @formatter:off
    http.authorizeRequests()
          // 認証対象外のパスを設定する。
          .antMatchers("/"
              , "/login"
              , "/login-error"
              , "/logout"
              , "/registration/**"
              , "/webjars/**"
              , "/vendor/**"
              , "/css/**"
              , "/js/**"
              , "/img/**"
              , "/imgs/**")
          .permitAll()
          // その他のリクエストは認証が必要
          .anyRequest().authenticated();
    http.csrf().disable().formLogin()
          // ログインフォームのパス
          .loginPage("/")
          // ログイン処理のパス
          .loginProcessingUrl("/login")
          // ログイン成功時の遷移先
          .defaultSuccessUrl("/top")
          // ログイン失敗時の遷移先
          .failureUrl("/login-error")
          // ログイン使用時のユーザ名input name
            .usernameParameter("loginId")
          // ログイン使用時のパスワードinput name
          .passwordParameter("password")
          .permitAll()
        .and()
          // 覚えておく期間(DBRepositoryもOK)
          .rememberMe()
          .tokenValiditySeconds(86400)
        .and()
          // ログアウト時の挙動
          .logout()
          .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
          .logoutSuccessUrl("/login?logout")
          // セッション破棄
          .invalidateHttpSession(true)
          // クッキー破棄（TODO;redisでもOKかと）
          .deleteCookies("JSESSIONID", "remember-me")
          .permitAll()
        .and()
//          .addFilter(preAuthenticatedProcessingFilter())
          .exceptionHandling()
//          .accessDeniedHandler(accessDeniedHandler())
          .authenticationEntryPoint(authenticationEntryPoint());
    // .tokenRepository(tokenRepository)
 // @formatter:on
  }

  @Bean
  AuthenticationEntryPoint authenticationEntryPoint() {
    return new SessionExpiredDetectingLoginUrlAuthenticationEntryPoint(
        "/login");
  }

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth)
      throws Exception {
    auth.authenticationProvider(authProvider)
    // .userDetailsService(userDetailService)
    ;
  }
}
