package com.freitech.kotetsu.forms.system.information;

import java.time.LocalDate;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import com.freitech.kotetsu.config.annotations.ViewAttribute;

import lombok.Data;

@Data
public class InformationSearchForm {

	@NotBlank(message = "")
	@ViewAttribute(name = "開始日", placeholder = "yyyy/mm/dd")
	@DateTimeFormat(pattern = "yyyy/mm/dd")
	private LocalDate startDate;

	@ViewAttribute(name = "終了日", placeholder = "yyyy/mm/dd")
	@DateTimeFormat(pattern = "yyyy/mm/dd")
	private LocalDate endDate;

}
