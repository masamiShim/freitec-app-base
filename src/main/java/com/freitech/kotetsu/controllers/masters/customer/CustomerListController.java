package com.freitech.kotetsu.controllers.masters.customer;

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
import com.freitech.kotetsu.forms.customer.CustomerSearchForm;
import com.freitech.kotetsu.service.CustomerService;

@Controller
@RequestMapping("/master/customer")
@ViewDef(layout = ColSize.ONE, title = "顧客一覧")
public class CustomerListController extends ListControllerBase<CustomerSearchForm> {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private HttpSession session;

	@GetMapping(path = INDEX)
	public String index(Model model) {

		CustomerSearchForm cond = searchCond();
		model.addAttribute("result", customerService.getSearchResutls(cond));
		model.addAttribute("cond", cond);

		return Path.CUSTOMER.getPath().concat(INDEX);
	}

	@PostMapping(path = INDEX)
	public String list(@Valid @ModelAttribute("cond") CustomerSearchForm cond, BindingResult error, Model model) {
		// エラーあればさよなら
		if (error.hasErrors()) {
			createBindingErrorView(error, model);
			return Path.CUSTOMER.getPath().concat(INDEX);
		}

		model.addAttribute("cond", cond);
		session.setAttribute("cond", cond);
		model.addAttribute("result", customerService.getSearchResutls(cond));

		// TODO: 検索条件が必要なら
		return Path.CUSTOMER.getPath().concat(INDEX);
	}

	protected CustomerSearchForm load() {
		return new CustomerSearchForm();
	}
}
