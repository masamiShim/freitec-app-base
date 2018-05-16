package com.freitech.kotetsu.atum.service;

import org.springframework.beans.factory.annotation.Value;

import com.freitech.kotetsu.atum.ModelItemList;

public abstract class AbstractScaffold implements Scaffold{

	@Value("${com.freitech.atum.outPutModelPath:}")
	private String outPutModelPath;

	abstract boolean createTemplate(ModelItemList list);

	@Override
	public boolean create(String beanName) {
		return createTemplate(parseClassAnnotations(beanName));
	}
	
	/**
	 * クラスのフィールドに設定されているAnnotationを
	 * @param modelClassName
	 * @return
	 */
	private ModelItemList parseClassAnnotations(String modelClassName) {
		return new ModelItemList(modelClassName);
	};

}
