package com.freitech.kotetsu.atum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.freitech.kotetsu.atum.service.ModelTemplateBuilderImpl;
import com.freitech.kotetsu.models.information.Information;

@RunWith(SpringJUnit4ClassRunner.class)
public class ModelTemplateBuilderTest {
	ModelTemplateBuilderImpl impl = new ModelTemplateBuilderImpl();
	
	@Test
	void buildTest() {
		ModelItemList list = new ModelItemList(Information.class.getName());
		impl.build(list);
	}
}
