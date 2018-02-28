package com.freitech.kotetsu.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@ToString
@EqualsAndHashCode(callSuper=false)
@Table(name = "Depertment")
public class Depertment extends SecurityAuditor{

  private static final long serialVersionUID = 1L;

  @Id
  @Column(name="depertmentId")
  private Long id;

}
