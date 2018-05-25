package com.freitech.kotetsu.controllers.masters.unitprice;

import java.util.Arrays;
import java.util.List;

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

import com.freitech.kotetsu.codes.ItemType;
import com.freitech.kotetsu.codes.Unit;
import com.freitech.kotetsu.config.annotations.ColSize;
import com.freitech.kotetsu.config.annotations.ViewDef;
import com.freitech.kotetsu.config.setting.Path;
import com.freitech.kotetsu.controllers.commons.ListControllerBase;
import com.freitech.kotetsu.db.repos.CustomerRepository;
import com.freitech.kotetsu.db.repos.ItemRepository;
import com.freitech.kotetsu.db.repos.UnitPriceRepository;
import com.freitech.kotetsu.forms.unitPrice.UnitPriceSearchForm;
import com.freitech.kotetsu.models.customer.Customer;
import com.freitech.kotetsu.models.item.Item;
import com.freitech.kotetsu.service.UnitPriceService;

@Controller
@RequestMapping("/master/unitprice")
@ViewDef(layout = ColSize.ONE, title = "単価設定")
public class UnitPriceListController extends ListControllerBase<UnitPriceSearchForm> {

	@Autowired
	private UnitPriceService unitPriceService;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private HttpSession session;

	@ModelAttribute(name = "customers")
	
	public List<Customer> getCustomers() {
		return customerRepository.findAll();
	}

	@ModelAttribute(name = "items")
	public List<Item> getItems() {
		return itemRepository.findAll();
	}

	@GetMapping(path = INDEX)
	public String index(Model model) {

		UnitPriceSearchForm cond = searchCond();
		model.addAttribute("result", unitPriceService.search(cond));
		model.addAttribute("cond", cond);

		return Path.UNITPRICE.getPath().concat(INDEX);
	}

	@PostMapping(path = INDEX)
	public String list(@Valid @ModelAttribute("cond") UnitPriceSearchForm cond, BindingResult error,
	                   Model model) {
		// エラーあればさよなら
		if (error.hasErrors()) {
			createBindingErrorView(error, model);
			return Path.UNITPRICE.getPath().concat(INDEX);
		}

		model.addAttribute("cond", cond);
		session.setAttribute("cond", cond);
		model.addAttribute("result", unitPriceService.search(cond));

		// TODO: 検索条件が必要なら
		return Path.UNITPRICE.getPath().concat(INDEX);
	}

	protected UnitPriceSearchForm load() {
		return new UnitPriceSearchForm();
	}
}
