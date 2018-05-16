package com.freitech.kotetsu.models.information;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import com.freitech.kotetsu.atum.annotations.CreatorSetting;
import com.freitech.kotetsu.config.annotations.ViewAttribute;
import com.freitech.kotetsu.models.SecurityAuditor;

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
	@CreatorSetting(displayDetail = true, editable = true)
	private String content;

	@NotNull
	@ViewAttribute(name = "開始日", placeholder = "2018/01/01")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@CreatorSetting(displayDetail = true, editable = true, searchCondition = true, inputType = "date")
	private LocalDate startDate;

	@ViewAttribute(name = "終了日", placeholder = "2018/12/28")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@CreatorSetting(displayDetail = true, editable = true, searchCondition = true, inputType = "date")
	private LocalDate endDate;

	public void setPersistInfo(Information other) {
		content = other.content;
		startDate = other.startDate;
		endDate = other.endDate;
	}
}
