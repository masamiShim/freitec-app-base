package com.freitech.kotetsu.models;

import java.util.Collection;
import java.util.Collections;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Users")
public class User extends SecurityAuditor implements UserDetails {

  private static final long serialVersionUID = 1L;

  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
  @Column(unique = true)
  private String userId;

  @Column(name = "LoginId", nullable = false)
  private String loginId;

  @Column(name = "Password", nullable = false)
  private String password;

  @Column(name = "Name")
  private String name;

  @Column(name = "Email")
  private String email;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "UserAttributeId")
  private UserAttributeEntity attribute;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "LoginAttributeId")
  private LoginLockAttributeEntity loginAttribute;

  @Column(name = "Locked")
  private boolean locked;

  @Column(name = "accountExpired")
  private boolean accountExpired;

  public User(String loginId, String password) {
    // super(loginId, password, true, true, true, true, new
    // HashSet<GrantedAuthority>());
    this.loginId = loginId;
    this.password = password;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return Collections.emptyList();
  }

  @Override
  public String getUsername() {
    return this.getLoginId();
  }

  @Override
  public boolean isAccountNonExpired() {
    // return !this.accountExpired;
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    // TODO Auto-generated method stub
    // return !this.locked;
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    // return false;
    return true;
  }

  @Override
  public boolean isEnabled() {
    // return !this.deleted;
    return true;
  }

}
