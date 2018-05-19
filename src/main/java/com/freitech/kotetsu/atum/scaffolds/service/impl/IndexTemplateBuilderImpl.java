package com.freitech.kotetsu.atum.scaffolds.service.impl;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.freitech.kotetsu.atum.ModelItemList;
import com.freitech.kotetsu.atum.builders.HtmlElementBuilder;
import com.freitech.kotetsu.atum.scaffolds.service.IndexTemplateBuilder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class IndexTemplateBuilderImpl implements IndexTemplateBuilder {

	// @formatter:off
	/*
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
		Map<String, Object> htmlAttr = new LinkedHashMap<>();
		htmlAttr.put("lang", "ja");
		htmlAttr.put("xmlns:th", "http://www.thymeleaf.org");
		htmlAttr.put("xmlns:layout", "http://www.ultraq.net.nz/thymeleaf/layout");
		htmlAttr.put("layout:decorator", "include/partial/list");
		
		return new HtmlElementBuilder()
			.append("<!DOCTYPE html>")
				.tag("html",htmlAttr).newl()
				// head部
				.append(new HtmlElementBuilder()
										.tag("head").newl()
										.append("<title>タイトル</title>")
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
		  .tag("th:block", "laoyout:fragment", "serchCond").newl();

		/*
		 * <div class="row"> <a class="btn btn-primary" th:href="@{/information/input}">新規登録</a> </div>
		 */
		Map<String, Object> attrs = new LinkedHashMap<>();
		attrs.put("class", "btn btn-primary");
		attrs.put("th:href", "@{/informmation/input}");

		builder.append(new HtmlElementBuilder()
		  .tag("div", "class", "row")
		  .tag("a", attrs).append("新規登録")
		  .close().toString())
		  .tag("div", "class", "x_panel")
		  .append(new HtmlElementBuilder()
		    .tag("div", "class", "x_title")
		    .append("<h4>検索条件</h4>")
		    .tag("div", "class", "clearfix")
		    .close().toString());

		HtmlElementBuilder form = new HtmlElementBuilder();
		Map<String, Object> formAttrs = new LinkedHashMap<>();
		attrs.put("class", "form-horizontal form-label-left");
		attrs.put("novalidate", "");
		attrs.put("th:action", "@{/information/index}");
		attrs.put("th:object", "${cond}");
		attrs.put("method", "POST");
		form.tag("form", formAttrs);
		// TODO:form-group繰り返し
		list.getModels().stream().forEach(item -> {
			if (item.isSearchCond()) {
				form.append(new HtmlElementBuilder()
				  .tag("div", "class", "form-group")
				  .tag("div", item.toIndexAttributeMap())
				  .tag("input", item.toAttributeBaseMap())
				  .close().toString());
			}
		});
		form.tag("div", "class", "form-group")
		  .tag("div", "class", "col-md-6 col-sm-6 col-xs-12 col-md-offset-3");

		// 検索ボタン
		Map<String, Object> button = new LinkedHashMap<>();
		button.put("type", "submit");
		button.put("class", "btn btn-success");
		button.put("name", "list");
		button.put("value", "list");
		form.append(new HtmlElementBuilder()
		  .tag("button", button)
		  .close().toString());

		return builder.append(form.toString()).close();
	}

	public HtmlElementBuilder buildTable(ModelItemList list) {
		HtmlElementBuilder builder = new HtmlElementBuilder();
		return builder.tag("th:block", "layout:fragment", "detail")
		  .tag("div", "class", "x_panel");

	}
}
