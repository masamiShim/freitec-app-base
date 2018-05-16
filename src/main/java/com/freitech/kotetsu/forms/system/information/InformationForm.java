package com.freitech.kotetsu.forms.system.information;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import com.freitech.kotetsu.models.information.Information;

import lombok.Data;

@Data
public class InformationForm {

  @NotBlank
  @Size(max = 255)
  private String content;

  @NotNull
  @DateTimeFormat(pattern="yyyy-MM-dd")
  private LocalDate startDate;

  @NotNull
  @DateTimeFormat(pattern="yyyy-MM-dd")
  private LocalDate endDate;

  public Information createEntity(){
    return new Information(content,startDate,endDate);
  }
}
