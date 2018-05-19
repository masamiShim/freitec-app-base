package com.freitech.kotetsu.forms.customer;

import java.time.LocalDate;

import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class CustomerSearchForm {

  @Size(max = 12)
  String corpName;

  @Size(max = 8)
  String customerNo;

  @Size(max = 50)
  String staffName;

  LocalDate dateFrom;

  LocalDate dateTo;

}
