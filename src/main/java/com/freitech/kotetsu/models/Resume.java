package com.freitech.kotetsu.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@SuppressWarnings("serial")
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
@Entity
public class Resume extends SecurityAuditor{
  @Id
  @Column(name="resumeId")
  private Long id;
  
  @Column(name="resume")
  private String resume;
}
