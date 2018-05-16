package com.freitech.kotetsu.config.tymeleaf;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.model.IModel;
import org.thymeleaf.model.IModelFactory;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractElementTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.spring4.context.SpringContextUtils;
import org.thymeleaf.templatemode.TemplateMode;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InputProcessor extends AbstractElementTagProcessor {

	private static final String NAME = "InputDialect";
	private static final String TAG_NAME = "mdl";
	private static final int PRECEDENCE = 1000;

	public InputProcessor(final String prefix) {
		super(TemplateMode.HTML, // This processor will apply only to HTML mode
				prefix, // Prefix to be applied to name for matching
				TAG_NAME, // Tag name: match specifically this tag
				true, // Apply dialect prefix to tag name
				null, // No attribute name: will match by tag name
				false, // No prefix to be applied to attribute name
				PRECEDENCE); // Precedence (inside dialect's own precedence)
	}

	@Override
	protected void doProcess(final ITemplateContext context, final IProcessableElementTag tag,
			final IElementTagStructureHandler structureHandler) {

		// applicationContextを取得
		final ApplicationContext appCtx = SpringContextUtils.getApplicationContext(context);
		// repositoryも取れるって
		// final HeadlineRepository headlineRepository =
		// appCtx.getBean(HeadlineRepository.class);

		/*
		 * Read the 'order' attribute from the tag. This optional attribute in our tag
		 * will allow us to determine whether we want to show a random headline or only
		 * the latest one ('latest' is default).
		 */
		final String beanName = tag.getAttributeValue("model");
		if (!appCtx.containsBean(beanName)) {
			log.warn(String.format("Thymeleaf not found beanName %s", beanName));
			return;
		}

		// デフォルトサイズ
		final String size = tag.getAttributeValue("size");

		/*
		 * Create the DOM structure that will be substituting our custom tag. The
		 * headline will be shown inside a '<div>' tag, and so this must be created
		 * first and then a Text node must be added to it.
		 */
		// Dom作るにはIModelFactory経由

		final IModelFactory modelFactory = context.getModelFactory();
		try {

			/*
			 * Instruct the engine to replace this entire element with the specified model.
			 */
			List<IModel> models = createInputElement(modelFactory, Class.forName(beanName), size);

			models.stream().forEach(m -> {
				structureHandler.insertImmediatelyAfter(m, false);
			});

		} catch (ClassNotFoundException cnfe) {
			log.warn(String.format("Class not found %s", beanName));
			return;
		}

	}

	/**
	 * input要素を作る
	 * 
	 * @param <T>
	 * 
	 * @param factory
	 * @param beanName
	 * @param name
	 * @return
	 */
	private <T> List<IModel> createInputElement(IModelFactory factory, Class<T> model, String name) {
		StandardModelFactoryWrapper fctry = new StandardModelFactoryWrapper(factory);
		List<IModel> elements = new ArrayList<>();
		for (Field f : model.getFields()) {
			// @formatter:off
			elements.add(fctry.open("div", "class", "row")
							  .open("div", "class", "col-md-")
							  .open("div", "class", "form-group")
							  .open("label", Collections.emptyMap())
							  .open("input", Collections.emptyMap())
							  // error
							  .open("div", Collections.emptyMap())
							  .close().build());
			// @formatter:on
		}
/*
Element label = new Element("label");
		label.setAttribute("for", vm.getName());
		String require = vm.isRequired() ? " require" : "";
		label.setAttribute("class", "form-control-label" + require);
		label.addChild(new Text(vm.getLabel()));
*/
		
/*
 		Element input = new Element("input");
		input.setAttribute("type", "text");
		input.setAttribute("id", vm.getName());
		input.setAttribute("class", "form-control");
		input.setAttribute("th:errorclass", "fieldError");
		input.setAttribute("th:field", "*{" + vm.getName() + "}");
		if (!vm.getPlaceholder().isEmpty()) {
			input.setAttribute("placeholder", vm.getPlaceholder());
		}

 */
		
		/*
		 		Element errorDiv = new Element("div");
		errorDiv.setAttribute("th:each", "err : ${#fields.hasErrors('" + vm.getName() + "')}");
		Element error = new Element("span");
		error.setAttribute("th:text", "${err}");
		// error.setAttribute("th:if", "${#fields.hasErrors('" + vm.getName() + "')}");
		// error.setAttribute("th:errors", "*{" + vm.getName() + "}");
		error.setAttribute("class", "help-block");
		error.addChild(new Text("error"));
		errorDiv.addChild(error);

		 */
		return elements;
	}


}
