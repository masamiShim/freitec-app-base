package com.freitech.kotetsu.atum.scaffolds.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.freitech.kotetsu.atum.ModelItemList;
import com.freitech.kotetsu.atum.builders.HtmlElementBuilder;
import com.freitech.kotetsu.atum.scaffolds.service.ModelTemplateBuilder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ModelTemplateBuilderImpl implements ModelTemplateBuilder {

	// @formatter:off
	/*<!DOCTYPE html>
	 * <html xmlns:th="http://www.thymeleaf.org"> 
	 *   <head> 
	 *     <meta charset="UTF-8" />
	 *   </head>
	 *  </html>
	 */
//@formatter:on
	@Override
	public String build(ModelItemList list) {
		// @formatter:off
		
		return new HtmlElementBuilder().append("<!DOCTYPE html>")
				.tag("html","xmlns:th","http://www.thymeleaf.org").newl()
				// head部
				.append(new HtmlElementBuilder()
										.tag("head").newl()
											.tag("meta","charset","UTF-8").newl()
										.close().toString())
				// body部
				.append(buildModelHtml(list).toString())
				.close().toString();
 
	}

// @formatter:off
/*
 <body>
	<div th:fragment="model(readonly)">
	  ↓↓↓↓↓↓　ここから繰り返し ↓↓↓↓↓↓
		<div class="form-group">
			<div
				th:replace="/partials/inputPartial::input('startDate', '開始日', ~{::#startDate})">
				<input id="startDate" type="date" required
					class="form-control col-md-7 col-xs-12" th:field="*{startDate}"
					th:attr="readonly='__${readonly}__'" placeholder="yyyy/mm/dd" />
			</div>
		</div>
	  ↑↑↑↑↑↑　ここから繰り返し ↑↑↑↑↑↑
	</div>
</body>	
 */
//@formatter:on
	private HtmlElementBuilder buildModelHtml(ModelItemList list) {
		HtmlElementBuilder builder = new HtmlElementBuilder();
		builder.tag("body").newl()
		  .tag("div", "th:fragment", "model(readonly").newl();

		list.getModels().forEach(item -> {
			Map<String, Object> attrs = item.toAttributeMap();
			// @formatter:off 
			HtmlElementBuilder input = new HtmlElementBuilder();
			input.tag("div","class", "form-group").newl()
						.tag("div","th:replace"
									,"inputPartial::input('" + item.getMember() + "', '" + item.getLabel() + "', ~{::#" + item.getMember() + "})").newl()
						.tag("input",attrs).newl().close();			
			builder.append(input.toString());
			});
			// @formatter:on

		return builder.close();
	}

}
