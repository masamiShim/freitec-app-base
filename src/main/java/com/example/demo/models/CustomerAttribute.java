package com.example.demo.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "Customer_Attribute")
public class CustomerAttribute {
  @Id
  @Column(name="customerAttributeId")
  private Long id;

}
