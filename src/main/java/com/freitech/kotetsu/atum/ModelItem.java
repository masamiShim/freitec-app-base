package com.freitech.kotetsu.atum;

import static org.junit.Assert.assertNotNull;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.freitech.kotetsu.atum.annotations.CreatorSetting;
import com.freitech.kotetsu.config.annotations.ViewAttribute;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModelItem {

	/** 変数 */
	private String member;

	private Class<?> clazz;

	private String inputType;

	private String label;

	private String placeholder;

	/** 編集可 */
	private boolean editable;

	/** 検索条件とするか */
	private boolean searchCond;

	/** 一覧表示するか */
	private boolean displayDetail;

	public ModelItem(Field field) {

		member = field.getName();
		clazz = field.getType();

		// ModelCreator用の設定を取得する。
		CreatorSetting[] setting = field.getDeclaredAnnotationsByType(CreatorSetting.class);
		assertNotNull(setting);

		editable = setting[0].editable();
		searchCond = setting[0].searchCondition();
		displayDetail = setting[0].displayDetail();
		inputType = setting[0].inputType();

		// 表示に関する設定
		ViewAttribute[] viewSetting = field.getDeclaredAnnotationsByType(ViewAttribute.class);
		if (viewSetting != null && viewSetting.length > 0) {
			label = viewSetting[0].name();
			placeholder = viewSetting[0].placeholder();
		}
	}

	public Map<String, Object> toAttributeMap() {
		Map<String, Object> attrs = toAttributeBaseMap();
		attrs.put("th:attr", "readonly='__${readonly}__'");
		return attrs;
	}

	public Map<String, Object> toIndexAttributeMap() {
		Map<String, Object> attrs = new LinkedHashMap<>();
		attrs.put("th:replace", "/partials/inputPartial::input('" + member + "', '" + label + "',~{::#"
		  + member + "})");
		return attrs;
	}
	public Map<String, Object> toAttributeBaseMap() {
		Map<String, Object> attrs = new LinkedHashMap<>();
		attrs.put("id", member);
		attrs.put("type", inputType);
		attrs.put("class", "form-control col-md-7 col-xs-12");
		attrs.put("th:field", "*{" + member + "}");
		if (StringUtils.isNotEmpty(placeholder)) {
			attrs.put("placeholder", placeholder);
		}
		return attrs;
	}
}
