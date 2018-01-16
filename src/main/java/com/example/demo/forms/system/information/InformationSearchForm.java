package com.example.demo.forms.system.information;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class InformationSearchForm {

  @DateTimeFormat(pattern = "yyyy-mm-dd")
  private LocalDate startDate;

  @DateTimeFormat(pattern = "yyyy-mm-dd")
  private LocalDate endDate;

}
