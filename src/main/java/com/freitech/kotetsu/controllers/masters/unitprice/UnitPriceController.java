package com.freitech.kotetsu.controllers.masters.unitprice;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.freitech.kotetsu.config.setting.Path;
import com.freitech.kotetsu.config.setting.PathBuilder;
import com.freitech.kotetsu.controllers.commons.SpringControllerBase2;
import com.freitech.kotetsu.db.repositories.CustomerRepository;
import com.freitech.kotetsu.db.repositories.ItemRepository;
import com.freitech.kotetsu.db.repositories.UnitPriceRepository;
import com.freitech.kotetsu.models.UnitPrice;
import com.freitech.kotetsu.models.customer.Customer;
import com.freitech.kotetsu.models.item.Item;
import com.freitech.kotetsu.service.UnitPriceService;

@Controller
@RequestMapping("/master/unitprice")
public class UnitPriceController extends SpringControllerBase2<UnitPrice> {

	@Autowired
	private UnitPriceService unitPriceService;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private UnitPriceRepository unitPriceReopsitory;

	@ModelAttribute(name = FORM_PARAM)
	public UnitPrice getForm() {
		return new UnitPrice();
	}

	
	@ModelAttribute(name = "customers")
	public List<Customer> getCustomers() {
		return customerRepository.findAll();
	}

	@ModelAttribute(name = "items")
	public List<Item> getItems() {
		return itemRepository.findAll();
	}

	@GetMapping(value = {"/input", "/input/{id}"})
	public String input(@PathVariable(required = false) Optional<Long> id, Model model) {
		// 初期値あれば設定
		id.ifPresent(i -> {
			unitPriceReopsitory.findByIdAndDeletedIsNull(i).ifPresent(info -> {
				model.addAttribute(FORM_PARAM, info);
			});
		});

		return new PathBuilder().join(Path.UNITPRICE).input().build();
	}

	@PostMapping(value = {"/input", "/input/{id}"})
	public String confirm(@Valid @ModelAttribute("form") UnitPrice form, BindingResult error,
	                      @PathVariable(required = false) Optional<Long> id, Model model) {

		model.addAttribute(FORM_PARAM, form);
		// エラーあればさよなら
		if (error.hasErrors()) {
			return new PathBuilder().join(Path.UNITPRICE).input().build();
		}
		return new PathBuilder().join(Path.UNITPRICE).confirm().build();
	}

	@PostMapping(value = {"/complete", "/complete/{id}"})
	@Transactional
	public String complete(@Valid @ModelAttribute("form") UnitPrice form, BindingResult result,
	                       @PathVariable(required = false) Optional<Long> id, Model model,
	                       RedirectAttributes attrs) {
		// エラーあればさよなら
		if (result.hasErrors()) {
			model.addAttribute("form", form);
			return new PathBuilder().join(Path.UNITPRICE).input().build();
		}

		UnitPrice registed = unitPriceService.regist(form);
		if (registed == null) {
			result.addError(new ObjectError("error", "登録/更新に失敗しました。再度お試しください。"));
			return new PathBuilder().join(Path.UNITPRICE).input().build();
		}
		model.addAttribute("success", "登録/更新しました。");
		return new PathBuilder().redirect().join(Path.UNITPRICE).input().join(registed.getId()).build();
	}

	@GetMapping(value = {"/detail/{id}"})
	public String detail(@PathVariable(required = true) Long id, Model model) {

		unitPriceReopsitory.findByIdAndDeletedIsNull(id).ifPresent(info -> {
			model.addAttribute(FORM_PARAM, info);
		});
		return new PathBuilder().join(Path.UNITPRICE).detial().build();
	}

	@GetMapping(value = {"/delete/{id}"})
	public String delete(@PathVariable(required = true) Long id, Model model,
	                     RedirectAttributes attrs) {

		if (unitPriceService.delete(id)) {
			attrs.addAttribute(SUCCESS_MESSAGE, "削除しました。");
		}
		else {
			attrs.addAttribute("error", "処理に失敗しました。");
		}
		return new PathBuilder().redirect().join(Path.UNITPRICE).index().build();
	}

}
