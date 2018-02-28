package com.freitech.kotetsu.config.annotations;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.thymeleaf.dialect.AbstractDialect;
import org.thymeleaf.processor.IProcessor;

import com.freitech.kotetsu.config.tymeleaf.InputDialect;

public class MyDialect extends AbstractDialect {
	private static final String PREFIX = "my";
	private static final Set<IProcessor> PROCESSORS;

	static {
		Set<IProcessor> tmp = new HashSet<>();
		tmp.add(new InputDialect());
		PROCESSORS = Collections.unmodifiableSet(tmp);
	}

	@Override
	public String getPrefix() {
		return PREFIX;
	}

	@Override
	public boolean isLenient() {
		return false;
	}

	@Override
	public Set<IProcessor> getProcessors() {
		return PROCESSORS;
	}

}
