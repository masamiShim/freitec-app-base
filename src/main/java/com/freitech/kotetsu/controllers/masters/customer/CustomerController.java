package com.freitech.kotetsu.controllers.masters.customer;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
import com.freitech.kotetsu.models.customer.Customer;
import com.freitech.kotetsu.models.customer.CutoffAfter;
import com.freitech.kotetsu.models.customer.CutoffDay;
import com.freitech.kotetsu.models.customer.IncludeTax;
import com.freitech.kotetsu.models.customer.PaymentAfter;
import com.freitech.kotetsu.models.customer.PaymentDay;
import com.freitech.kotetsu.models.customer.Rounding;
import com.freitech.kotetsu.service.CustomerService;

@Controller
@RequestMapping("/master/customer")
public class CustomerController extends SpringControllerBase2<Customer> {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private CustomerRepository customerReopsitory;

	@ModelAttribute(name = FORM_PARAM)
	public Customer getForm() {
		return new Customer();
	}

	@ModelAttribute(name = "cutoffAfter")
	public List<CutoffAfter> getCutoffAfter() {
		return Arrays.asList(CutoffAfter.values());
	}

	@ModelAttribute(name = "cutoffDay")
	public List<CutoffDay> getCutoffDay() {
		return Arrays.asList(CutoffDay.values());
	}

	@ModelAttribute(name = "paymentDay")
	public List<PaymentDay> getPaymentDay() {
		return Arrays.asList(PaymentDay.values());
	}

	@ModelAttribute(name = "paymentAfter")
	public List<PaymentAfter> getPaymentAfter() {
		return Arrays.asList(PaymentAfter.values());
	}

	@ModelAttribute(name = "includeTax")
	public List<IncludeTax> getIncludeTax() {
		return Arrays.asList(IncludeTax.values());
	}

	@ModelAttribute(name = "rounding")
	public List<Rounding> getRounding() {
		return Arrays.asList(Rounding.values());
	}

	@GetMapping(value = {"/input", "/input/{id}"})
	public String input(@PathVariable(required = false) Optional<Long> id, Model model) {
		// 初期値あれば設定
		id.ifPresent(i -> {
			customerReopsitory.findByIdAndDeletedIsNull(i).ifPresent(m -> {
				m.setAttribute(m.getLatestAttribute());
				model.addAttribute(FORM_PARAM, m);
			});
		});

		return new PathBuilder().join(Path.CUSTOMER).input().build();
	}

	@PostMapping(value = {"/input",
	  "/input/{id}"})
	public String confirm(@Valid @ModelAttribute("form") Customer form, BindingResult error,
	                      @PathVariable(required = false) Optional<Long> id, Model model) {

		model.addAttribute(FORM_PARAM, form);
		// エラーあればさよなら
		if (error.hasErrors()) {
			return new PathBuilder().join(Path.CUSTOMER).input().build();
		}
		return new PathBuilder().join(Path.CUSTOMER).confirm().build();
	}

	@PostMapping(value = {"/complete", "/complete/{id}"})
	@Transactional
	public String complete(
	                       @Valid @ModelAttribute("form") Customer form,
	                       BindingResult result,
	                       @PathVariable(required = false) Optional<Long> id, Model model,
	                       RedirectAttributes attrs) {
		// エラーあればさよなら
		if (result.hasErrors()) {
			model.addAttribute("form", form);
			return new PathBuilder().join(Path.CUSTOMER).input().build();
		}

		form.merge(form);
		Customer registed = customerService.regist(form);
		model.addAttribute("success", "登録/更新しました。");
		return new PathBuilder().redirect().join(Path.CUSTOMER).input().join(registed.getId()).build();
	}

	@GetMapping(value = {"/detail/{id}"})
	public String detail(@PathVariable(required = true) Long id, Model model) {

		customerReopsitory.findByIdAndDeletedIsNull(id).ifPresent(info -> {
			info.setAttribute(info.getLatestAttribute());
			model.addAttribute(FORM_PARAM, info);
		});
		return new PathBuilder().join(Path.CUSTOMER).detial().build();
	}

	@GetMapping(value = {"/delete/{id}"})
	public String delete(@PathVariable(required = true) Long id, Model model,
	                     RedirectAttributes attrs) {

		if (customerService.delete(id)) {
			attrs.addAttribute(SUCCESS_MESSAGE, "削除しました。");
		}
		else {
			attrs.addAttribute("error", "処理に失敗しました。");
		}
		return new PathBuilder().redirect().join(Path.CUSTOMER).index().build();
	}

}
