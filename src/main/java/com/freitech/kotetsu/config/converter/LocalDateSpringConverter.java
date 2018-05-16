package com.freitech.kotetsu.config.converter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.converter.Converter;

public class LocalDateSpringConverter implements Converter<String, LocalDate> {

	private final DateTimeFormatter formatter;

	public LocalDateSpringConverter(String dateFormat) {
		this.formatter = DateTimeFormatter.ofPattern(dateFormat);
	}

	@Override
	public LocalDate convert(String source) {
		if (StringUtils.isEmpty(source)) {
			return null;
		}
		return LocalDate.parse(source, formatter);
	}

}
