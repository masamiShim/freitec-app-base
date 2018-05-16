package com.freitech.kotetsu.atum.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.freitech.kotetsu.atum.ModelItemList;

@Component("ScaffordGellateriaImpl")
public class ScaffordGellateriaImpl extends AbstractScaffold {

	@Autowired
	ModelTemplateBuilder modelTemplateBuilder;

// @Autowired
// IndexScaffoldService indexScaffoldService;

// @Autowired
// DetailScaffoldService detailScaffoldService;

	@Override
	boolean createTemplate(ModelItemList list) {
		modelTemplateBuilder.build(list);
		return false;
	}

}
