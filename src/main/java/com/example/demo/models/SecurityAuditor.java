package com.example.demo.models;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

@Data
@EntityListeners(value = AuditingEntityListener.class)
@MappedSuperclass
public abstract class SecurityAuditor implements Serializable {

  private static final long serialVersionUID = 1L;

  @CreatedBy
  @Column(name = "createdBy")
  protected String createdBy;

  @CreatedDate
  @Column(name = "created")
  protected LocalDateTime created;

  @LastModifiedBy
  @Column(name = "modifiedBy")
  protected String modifiedBy;

  @LastModifiedDate
  @Column(name = "modified")
  protected LocalDateTime modified;

  @Column(name = "deleted")
  protected boolean deleted;

  @Version
  protected long version;
}
