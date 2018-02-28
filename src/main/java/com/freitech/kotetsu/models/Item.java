package com.freitech.kotetsu.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.freitech.kotetsu.codes.ItemType;
import com.freitech.kotetsu.codes.Unit;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "Item")
public class Item extends SecurityAuditor {

  private static final long serialVersionUID = 1L;
  @Id
  @Column(name = "itemId")
  private Long id;

  /** 品番 */
  @Column(name = "itemNo")
  private String itemNo;

  @OneToMany(fetch = FetchType.EAGER)
  @JoinColumn(name = "unitPriceId")
  private List<UnitPrice> price;

  @Enumerated(EnumType.STRING)
  @Column(name = "type")
  private ItemType type;

  /** 品名 */
  @Column(name = "name")
  private String name;

  @Enumerated(EnumType.STRING)
  @Column(name = "unit")
  private Unit unit;

}
