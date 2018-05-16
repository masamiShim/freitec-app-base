package com.freitech.kotetsu.models;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import javax.validation.Validator;
import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.AssertTrue;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.freitech.kotetsu.WebApplicationTests;
import com.freitech.kotetsu.forms.system.information.InformationSearchForm;

@SpringBootTest(classes = WebApplicationTests.class)
public class InformationTest {

	InformationSearchForm searchForm = new InformationSearchForm();

	Validator validator;

	@Test
	@AssertTrue
	public void checkFromTo() {
		searchForm.getFromTo().setStartDate(LocalDate.of(2018, 1, 10));
		searchForm.getFromTo().setEndDate(LocalDate.of(2018, 1, 20));
		assertThat(validator.validate(searchForm).isEmpty());
	}

	@Test
	@AssertFalse
	public void checkFromToFalse() {
		searchForm.getFromTo().setStartDate(LocalDate.of(2018, 1, 20));
		searchForm.getFromTo().setEndDate(LocalDate.of(2018, 1, 10));
		assertThat(validator.validate(searchForm)).isEmpty();
	}

	@Test
	public void unCheckStartEmpty() {
		searchForm.getFromTo().setEndDate(LocalDate.of(2018, 1, 20));
		assertThat(validator.validate(searchForm)).isEmpty();
	}

	@Test
	public void unCheckEndEmpty() {
		searchForm.getFromTo().setStartDate(LocalDate.of(2018, 1, 20));
		assertThat(validator.validate(searchForm)).isEmpty();
	}

	@Test
	public void emptyDate() {
		assertThat(validator.validate(searchForm)).isEmpty();
	}

}
