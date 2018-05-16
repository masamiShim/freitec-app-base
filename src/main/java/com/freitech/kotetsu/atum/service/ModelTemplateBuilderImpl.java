package com.freitech.kotetsu.atum.service;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.stereotype.Service;

import com.freitech.kotetsu.atum.ModelItemList;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ModelTemplateBuilderImpl implements ModelTemplateBuilder {

	@Override
	public void build(ModelItemList list) {

		Document doc = DocumentHelper.createDocument();

		// header作成
		Element html = buildHeaderHTML(DocumentHelper.createElement("html"), list);
//		doc.add(html);
		log.info(html.toString());
		// body作成
		Element body = buildModelHtml(DocumentHelper.createElement("body"), list);
		log.info(body.toString());
		html.add(body);
		doc.add(html);
		log.info(doc.toString());
	}

//@formatter:off
	/*
	 * <html xmlns:th="http://www.thymeleaf.org"> 
	 *   <head> 
	 *     <meta charset="UTF-8" />
	 *   </head>
	 *  </html>
	 */
//@formatter:on
	private Element buildHeaderHTML(Element html, ModelItemList list) {
		html.addAttribute("xmlns:th", "http://www.thymeleaf.org");
		Element head = DocumentHelper.createElement("head");
		Element meta = DocumentHelper.createElement("meta");
		meta.addAttribute("charset", "UTF-8");
		head.appendAttributes(meta);

		html.appendAttributes(meta);
		return html;
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
	private Element buildModelHtml(Element body, ModelItemList list) {

		Element fragment = DocumentHelper.createElement("div")
		  .addAttribute("th:fragment", "model(readonly");

		list.getModels().forEach(item -> {
			Element formGroup = DocumentHelper.createElement("div")
			  .addAttribute("class", "form-group");

			Element inputElem = DocumentHelper.createElement("div")
			  .addAttribute("th:replace",
			    "inputPartial::input('" + item.getMember() + "', '" + item.getLabel() + "', ~{::#" + item
			      .getMember() + "})");

			Element inputBody = DocumentHelper.createElement("input")
			  .addAttribute("id", item.getMember())
			  .addAttribute("type", item.getInputType())
			  // viewの大きさ変えたい場合はこっちも
			  .addAttribute("class", "form-control col-md-7 col-xs-12")
			  // ここメンバ
			  .addAttribute("th:field", "*{" + item.getMember() + "}")
			  // 定型文
			  .addAttribute("th:attr", "readonly='__${readonly}__'")
			  // 入れるか
			  .addAttribute("placeholder", item.getPlaceholder());
			inputElem.add(inputBody);
			formGroup.add(inputElem);
			fragment.add(formGroup);
			System.out.println(inputBody.toString());
			System.out.println(inputElem.toString());
			System.out.println(formGroup.toString());
			System.out.println(fragment.toString());
		});

		body.add(fragment);
		System.out.println(body.toString());

		return body;

	}

}
