package com.freitech.kotetsu.forms.estimate;

import java.time.LocalDate;

import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class EstimateSearchForm {

  @Size(max = 12)
  String no;

  @Size(max = 8)
  String customerNo;

  @Size(max = 50)
  String staffName;

  LocalDate dateFrom;

  LocalDate dateTo;

}
