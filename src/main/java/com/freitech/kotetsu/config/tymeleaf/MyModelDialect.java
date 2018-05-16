package com.freitech.kotetsu.config.tymeleaf;

import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.engine.AttributeName;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractAttributeTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.templatemode.TemplateMode;

public class MyModelDialect extends AbstractAttributeTagProcessor {

	protected MyModelDialect(TemplateMode templateMode, String dialectPrefix, String elementName,
			boolean prefixElementName, String attributeName, boolean prefixAttributeName, int precedence,
			boolean removeAttribute) {
		super(templateMode, dialectPrefix, elementName, prefixElementName, attributeName, prefixAttributeName, precedence,
				removeAttribute);
	}

	@Override
	protected void doProcess(ITemplateContext arg0, IProcessableElementTag arg1, AttributeName arg2, String arg3,
			IElementTagStructureHandler arg4) {
		// TODO Auto-generated method stub
		
	}


}
