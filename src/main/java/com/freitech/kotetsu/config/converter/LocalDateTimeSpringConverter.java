package com.freitech.kotetsu.config.converter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.converter.Converter;

public class LocalDateTimeSpringConverter implements Converter<String, LocalDateTime> {

	private final DateTimeFormatter formatter;

	public LocalDateTimeSpringConverter(String dateFormat) {
		this.formatter = DateTimeFormatter.ofPattern(dateFormat);
	}

	@Override
	public LocalDateTime convert(String source) {
		if (StringUtils.isEmpty(source)) {
			return null;
		}
		return LocalDateTime.parse(source, formatter);
	}

}
