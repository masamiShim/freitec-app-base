package com.freitech.kotetsu.forms;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;

import com.freitech.kotetsu.models.EstimateDetail;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Component
public class EstimateForm implements FormBase{

  private Long id;

  private String estimateNo;

  @NotEmpty(message="タイトルは設定してね")
  @Size(max=255, message="もじすうがー")
  private String title;

  @NotEmpty(message="取引先は設定してね")
  private Long customerId;

  private String customerName;

  @NotEmpty(message="担当者は設定してね")
  private Long staffId;

  @NotEmpty(message="見積もり日を設定してね")
  private LocalDate estimateDate;

  @NotEmpty(message="見積もり期限を設定してね")
  private LocalDate expired;

  private Long prevEstimateId;
  
  private Long prevEstimateNo;

  @NotEmpty(message="明細ないよ")
  private List<EstimateDetail> details;

}
