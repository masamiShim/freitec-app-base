package com.freitech.kotetsu.controllers.system.information;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.freitech.kotetsu.config.annotations.ColSize;
import com.freitech.kotetsu.config.annotations.ViewDef;
import com.freitech.kotetsu.config.setting.Path;
import com.freitech.kotetsu.controllers.commons.ListControllerBase;
import com.freitech.kotetsu.forms.system.information.InformationSearchForm;
import com.freitech.kotetsu.models.information.InformationListModel;
import com.freitech.kotetsu.service.InformationService;

@Controller
@RequestMapping("/information")
@ViewDef(layout = ColSize.ONE, title = "お知らせ一覧")
public class InformationListController extends ListControllerBase<InformationSearchForm> {

	@Autowired
	private InformationService informationService;

	@Autowired
	private HttpSession session;

	private InformationListModel listModel;

	@GetMapping(path = INDEX)
	public String index(Model model) {

		InformationSearchForm cond = searchCond();
		model.addAttribute("result", listModel.of(informationService.getInformationList(cond)));
		model.addAttribute("cond", cond);

		return Path.INFORMATION.getPath().concat(INDEX);
	}

	@PostMapping(path = INDEX)
	public String list(@Valid @ModelAttribute("cond") InformationSearchForm cond, BindingResult error,
	                   Model model) {
		// エラーあればさよなら
		if (error.hasErrors()) {
			createBindingErrorView(error, model);
			return Path.INFORMATION.getPath().concat(INDEX);
		}

		model.addAttribute("cond", cond);
		session.setAttribute("cond", cond);

		// TODO: 検索条件が必要なら
		return Path.INFORMATION.getPath().concat(INDEX);
	}

	protected InformationSearchForm load() {
		return new InformationSearchForm();
	}
}
