package com.example.demo.models;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@EqualsAndHashCode(callSuper = false)
@ToString
@Data
@Entity
@Table(name = "Information")
@AllArgsConstructor
@NoArgsConstructor
public class Information extends SecurityAuditor {

  private static final long serialVersionUID = 1L;

  public Information(String content, LocalDate startDate, LocalDate endDate) {
    this.content = content;
    this.startDate = startDate;
    this.endDate = endDate;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String content;

  private LocalDate startDate;

  private LocalDate endDate;

}
