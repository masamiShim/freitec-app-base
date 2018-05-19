package com.freitech.kotetsu.atum.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.freitech.kotetsu.atum.ModelItemList;
import com.freitech.kotetsu.atum.scaffolds.service.IndexTemplateBuilder;
import com.freitech.kotetsu.atum.scaffolds.service.ModelTemplateBuilder;

@Component("ScaffordGellateriaImpl")
public class ScaffordGellateriaImpl extends AbstractScaffold {

	@Autowired
	ModelTemplateBuilder modelTemplateBuilder;

 @Autowired
 IndexTemplateBuilder indexScaffoldService;

// @Autowired
// DetailScaffoldService detailScaffoldService;

	@Override
	boolean createTemplate(ModelItemList list) {
		// Model作成
		String html = modelTemplateBuilder.build(list);
		System.out.println(html);
		return false;
	}

}
