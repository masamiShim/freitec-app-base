package com.freitech.kotetsu.models;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 単価
 * @author shimbuichi
 *
 */
@Data
@Entity
@ToString
@EqualsAndHashCode(callSuper=false)
@Table(name = "Unit_Price")
public class UnitPrice extends SecurityAuditor implements PriceBase{
  
  private static final long serialVersionUID = 1L;
 
  @Id
  @Column(name="unitPriceId")
  private Long id;
  
  @ManyToOne(fetch=FetchType.LAZY)
  @JoinColumn(name="customerId")
  private Customer customer;

  @Column(name="identityName")
  private String identityName;
  
  @NotNull
  @Column(name="price")
  private BigDecimal price;

  @NotNull
  @Column(name="expired")
  private LocalDate expired;

}

