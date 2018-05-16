package com.freitech.kotetsu.service.inforamtion.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.freitech.kotetsu.AbstractTest;
import com.freitech.kotetsu.config.setting.Path;
import com.freitech.kotetsu.config.setting.PathBuilder;
import com.freitech.kotetsu.service.InformationService;

@AutoConfigureWebMvc
public class InformationListControllerTest extends AbstractTest {

	@Autowired
	private MockMvc mock;

	@MockBean
	private InformationService ifs;

	@Test
	private void index疎通チェック() throws Exception {
		mock.perform(get(new PathBuilder().join(Path.INFORMATION).index().build())).andExpect(status().isOk())
				.andExpect(content().string("一覧"));
	}
}
