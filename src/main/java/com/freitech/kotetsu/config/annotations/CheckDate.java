package com.freitech.kotetsu.config.annotations;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;

import org.apache.commons.lang3.StringUtils;

@Target({ FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = { com.freitech.kotetsu.config.annotations.CheckDate.DateValidator.class })
public @interface CheckDate {

	String message() default "input date {date} is not valid";

	String dateFormat() default "yyyy-MM-dd";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
	@Retention(RetentionPolicy.RUNTIME)
	@Documented
	@interface List {
		CheckDate[] value();
	}

	class DateValidator implements ConstraintValidator<CheckDate, Object> {

		DateTimeFormatter dateFormat;

		@Override
		public void initialize(CheckDate date) {
			dateFormat = DateTimeFormatter.ofPattern(date.dateFormat());
		}

		@Override
		public boolean isValid(Object date, ConstraintValidatorContext context) {
			if (date == null || StringUtils.isEmpty(date.toString())) {
				return true;
			}

			try {
				LocalDate.parse(date.toString(), dateFormat);
			} catch (DateTimeParseException dtpe) {
				return false;
			}
			return true;
		}
	}
}
