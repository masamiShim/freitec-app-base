package com.freitech.kotetsu.forms.system.information;

import java.time.LocalDate;

import com.freitech.kotetsu.forms.bases.FromToSearchForm;

import lombok.Data;

@Data
public class InformationSearchForm {

	FromToSearchForm fromTo;

	public InformationSearchForm() {
		fromTo.of(LocalDate.now());
	}
}
