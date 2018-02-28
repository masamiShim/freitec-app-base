package com.freitech.kotetsu.models;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.freitech.kotetsu.config.annotations.Date;
import com.freitech.kotetsu.config.annotations.ViewAttribute;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@SuppressWarnings("serial")
@EqualsAndHashCode(callSuper = false)
@ToString
@Data
@Entity
@Table(name = "Information")
@AllArgsConstructor
@NoArgsConstructor
public class Information extends SecurityAuditor {

	@NotBlank
	@ViewAttribute(name = "内容", placeholder = "18:00からメンテナンスを行います")
	@Length(max = 4000)
	private String content;

	@NotNull
	@Date
	@ViewAttribute(name = "開始日", placeholder = "2018/01/01")
	private LocalDate startDate;

	@Date
	@ViewAttribute(name = "終了日", placeholder = "2018/12/28")
	private LocalDate endDate;

}
