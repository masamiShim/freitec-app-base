package com.freitech.kotetsu.config.tymeleaf;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.attoparser.dom.Text;
import org.thymeleaf.context.IExpressionContext;
import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.dialect.IExpressionObjectDialect;
import org.thymeleaf.expression.IExpressionObjectFactory;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.standard.StandardDialect;
import org.thymeleaf.standard.processor.StandardXmlNsTagProcessor;
import org.thymeleaf.templatemode.TemplateMode;

public class MyDialect extends AbstractProcessorDialect implements IExpressionObjectDialect {

	private static final String NAME = "My Dialect";
	private static final String PREFIX = "my";
	private static final String EXPRESSION = "text";

	public MyDialect() {
		this(PREFIX);
	}

	public MyDialect(String prefix) {
		super(NAME, prefix, StandardDialect.PROCESSOR_PRECEDENCE);
	}

	@Override
	public Set<IProcessor> getProcessors(String prefix) {
		final Set<IProcessor> processors = new HashSet<IProcessor>();
	//	processors.add(new MyProcessor(prefix));
		processors.add(new StandardXmlNsTagProcessor(TemplateMode.HTML, prefix));
		return processors;
	}

	@Override
	public IExpressionObjectFactory getExpressionObjectFactory() {
		return new IExpressionObjectFactory() {

			@Override
			public boolean isCacheable(String arg0) {
				return false;
			}

			@Override
			public Set<String> getAllExpressionObjectNames() {
				return Collections.singleton(NAME);
			}

			@Override
			public Object buildObject(IExpressionContext context, String expression) {
				 return (EXPRESSION.equals(expression)) ? new Text("user-") : null;
			}
		};
	}

}
