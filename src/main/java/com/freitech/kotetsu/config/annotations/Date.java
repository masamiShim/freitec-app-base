package com.freitech.kotetsu.config.annotations;

import static java.lang.annotation.ElementType.*;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;

import org.apache.commons.lang3.StringUtils;

@Target({ FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = { com.freitech.kotetsu.config.annotations.Date.DateValidator.class })
public @interface Date {

	String message() default "input date {date} is not valid";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
	@Retention(RetentionPolicy.RUNTIME)
	@Documented
	@interface List {
		Date[] value();
	}

	class DateValidator implements ConstraintValidator<Date, String> {

		@Override
		public void initialize(Date date) {
		}

		@Override
		public boolean isValid(String date, ConstraintValidatorContext context) {
			if (StringUtils.isBlank(date)) {
				return true;
			}
			try {
				LocalDate.parse(date);
			} catch (DateTimeParseException dtpe) {
				return false;
			}
			return true;
		}
	}
}
