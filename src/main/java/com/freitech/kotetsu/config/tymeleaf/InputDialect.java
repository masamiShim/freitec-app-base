package com.freitech.kotetsu.config.tymeleaf;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.thymeleaf.Arguments;
import org.thymeleaf.Configuration;
import org.thymeleaf.context.IContext;
import org.thymeleaf.dom.Element;
import org.thymeleaf.dom.Text;
import org.thymeleaf.processor.attr.AbstractConditionalVisibilityAttrProcessor;
import org.thymeleaf.spring4.context.SpringWebContext;
import org.thymeleaf.standard.expression.IStandardExpression;
import org.thymeleaf.standard.expression.IStandardExpressionParser;
import org.thymeleaf.standard.expression.StandardExpressions;

public class InputDialect extends AbstractConditionalVisibilityAttrProcessor {

	private static final String ATTR_NAME = "elm";
	private static final int PRECEDENCE = 1000;

	public InputDialect() {
		super(ATTR_NAME);
	}

	@Override
	public int getPrecedence() {
		return PRECEDENCE;
	}

	@Override
	protected boolean isVisible(Arguments arg, Element elem, String attributeName) {
		final IContext context = arg.getContext();
		final SpringWebContext springWebContext = (SpringWebContext) context;

		final Configuration configuration = arg.getConfiguration();
		final IStandardExpressionParser parser = StandardExpressions.getExpressionParser(configuration);
		final IStandardExpression expression = parser.parseExpression(configuration, arg, "${v}");

		return Optional.ofNullable((ViewModel) expression.execute(configuration, arg)).map(vm -> {
			if (vm.isVisible()) {
				System.out.println(vm.toString());
				configureElem(vm, elem, springWebContext.getHttpServletRequest());
			}
			return vm.isVisible();
		}).orElse(false);
	}

	// @formatter:off
	// <div class="colsize">
	// <div class="form-group">
	// <label for="id" class="require">hoge</label>
	// <input id="required-input" class="form-control" value="Input value" />
	// <span th:if="${#fields.hasErrors('fdate')}" th:errors="*{fdate}"
	// class="help-block">error!</span>
	// </div> </div>
	// @formatter:on
	private void configureElem(ViewModel vm, Element elem, HttpServletRequest request) {
		Element row = new Element("div");
		row.setAttribute("class", "row");

		Element col = new Element("div");
		col.setAttribute("class", "col-md-" + vm.getColSize());

		Element fg = new Element("div");
		fg.setAttribute("class", "form-group");

		Element label = createLabel(vm);
		Element input = createInput(vm);
		Element error = createError(vm);

		fg.addChild(label);
		fg.insertAfter(label, input);
		fg.insertAfter(input, error);
		col.addChild(fg);
		row.addChild(col);

		elem.getParent().insertBefore(elem, row);

	}

	private Element createInput(ViewModel vm) {
		Element input = new Element("input");
		input.setAttribute("type", "text");
		input.setAttribute("id", vm.getName());
		input.setAttribute("class", "form-control");
		input.setAttribute("th:errorclass", "fieldError");
		input.setAttribute("th:field", "*{" + vm.getName() + "}");
		if (!vm.getPlaceholder().isEmpty()) {
			input.setAttribute("placeholder", vm.getPlaceholder());
		}
		return input;
	}

	// @formatter:off
	// <span th:if="${#fields.hasErrors('fdate')}" th:errors="*{fdate}"
	// class="help-block">error!</span>
	// @formatter:on
	// <ul>
	// <li th:each="err : ${#fields.errors('datePlanted')}" th:text="${err}" />
	// </ul>
	private Element createError(ViewModel vm) {
		Element errorDiv = new Element("div");
		errorDiv.setAttribute("th:each", "err : ${#fields.hasErrors('" + vm.getName() + "')}");
		Element error = new Element("span");
		error.setAttribute("th:text", "${err}");
		// error.setAttribute("th:if", "${#fields.hasErrors('" + vm.getName() + "')}");
		// error.setAttribute("th:errors", "*{" + vm.getName() + "}");
		error.setAttribute("class", "help-block");
		error.addChild(new Text("error"));
		errorDiv.addChild(error);
		return errorDiv;
	}

	private Element createLabel(ViewModel vm) {
		Element label = new Element("label");
		label.setAttribute("for", vm.getName());
		String require = vm.isRequired() ? " require" : "";
		label.setAttribute("class", "form-control-label" + require);
		label.addChild(new Text(vm.getLabel()));
		return label;
	}

}
