package com.freitech.kotetsu.models;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.freitech.kotetsu.codes.SectionType;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name="Estimate_Detail")
public class EstimateDetail extends SecurityAuditor{

  private static final long serialVersionUID = 1L;

  @Id
  @Column(name="estimateDetailId")
  private Long id;
  
  /** 順序  */
  @Column(name="ordinary")
  private int ordinary;

  @OneToOne(fetch=FetchType.LAZY)
  @JoinColumn(name="estimateId")
  private Estimate estimate;

  @Column(name="section")
  private SectionType section;

  @Column(name="content")
  private String content;

  /** 商品 */
  @ManyToOne(fetch=FetchType.LAZY)
  @JoinColumn(name="itemId")
  private Item item;
  
  /** 価格 */
  @Column(name="price")
  private BigDecimal price;

  /** 個数 */
  @Column(name="quantity")
  private int quantity;
  
  
}
