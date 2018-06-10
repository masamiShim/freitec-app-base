package com.freitech.kotetsu.forms.customer;

import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class CustomerSearchForm {

  @Size(max = 8)
  String customerNo;

  @Size(max = 12)
  String corpName;

}
