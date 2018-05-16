package com.freitech.kotetsu.atum;

import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Data
public class ModelItemList {

	private Class<?> clazz;

	private List<ModelItem> models;

	public ModelItemList(String beanName) {
		assertNotNull(beanName);

		try {
			this.clazz = Class.forName(beanName);

			models = Arrays.stream(clazz.getDeclaredFields())
			  .filter(f -> except(f.getName()))
			  .map(ModelItem::new)
			  .collect(Collectors.toList());
		}
		catch (ClassNotFoundException e) {
			System.err.println("クラス見つかりまへん");
			assertNotNull(this.clazz);
		}

	}

	private boolean except(String fieldName) {
		return exceptKeys().stream().filter(key -> fieldName.contains(key)).count() == 0;
	}

	/**
	 * 変数名で生成する項目から除外するキーワード
	 * @return
	 */
	private List<String> exceptKeys() {
		// TODO: ymlでカンマ区切りとかでもよいかも
		return Arrays.asList(new String[] {"serialVersionUID"});
	}
}
