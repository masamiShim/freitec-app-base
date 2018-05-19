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
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.freitech.kotetsu.codes.ItemType;
import com.freitech.kotetsu.codes.Unit;
import com.freitech.kotetsu.config.setting.Path;
import com.freitech.kotetsu.config.setting.PathBuilder;
import com.freitech.kotetsu.controllers.commons.SpringControllerBase2;
import com.freitech.kotetsu.db.repos.ItemRepository;
import com.freitech.kotetsu.models.item.Item;
import com.freitech.kotetsu.service.ItemService;

@Controller
@RequestMapping("/master/item")
public class CustomerController extends SpringControllerBase2<Item> {

	@Autowired
	private ItemService itemService;

	@Autowired
	private ItemRepository itemReopsitory;

	@ModelAttribute(name = FORM_PARAM)
	public Item getForm() {
		return new Item();
	}
	
	@ModelAttribute(name = "types")
	public List<ItemType> getItemTypes() {
		return Arrays.asList(ItemType.values());
	}

	@ModelAttribute(name = "units")
	public List<Unit> getUnit() {
		return Arrays.asList(Unit.values());
	}


	@GetMapping(value = {"/input", "/input/{id}"})
	public String input(@PathVariable(required = false) Optional<Long> id, Model model) {
		// 初期値あれば設定
		id.ifPresent(i -> {
			itemReopsitory.findById(i).ifPresent(info -> {
				model.addAttribute(FORM_PARAM, info);
			});
		});

		return new PathBuilder().join(Path.ITEM).input().build();
	}

	@PostMapping(value = {"/input", "/input/{id}"})
	public String confirm(@Valid @ModelAttribute("form") Item form, BindingResult error,
	                      @PathVariable(required = false) Optional<Long> id, Model model) {

		model.addAttribute(FORM_PARAM, form);
		// エラーあればさよなら
		if (error.hasErrors()) {
			return new PathBuilder().join(Path.ITEM).input().build();
		}
		return new PathBuilder().join(Path.ITEM).confirm().build();
	}

	@PostMapping(value = {"/complete", "/complete/{id}"})
	@Transactional
	public String complete(@Valid @ModelAttribute("form") Item form, BindingResult result,
	                       @PathVariable(required = false) Optional<Long> id, Model model,
	                       RedirectAttributes attrs) {
		// エラーあればさよなら
		if (result.hasErrors()) {
			model.addAttribute("form", form);
			return new PathBuilder().join(Path.ITEM).input().build();
		}

		Item registed = itemService.regist(form);
		if (registed == null) {
			result.addError(new ObjectError("error", "登録/更新に失敗しました。再度お試しください。"));
			return new PathBuilder().join(Path.ITEM).input().build();
		}
		model.addAttribute("success", "登録/更新しました。");
		return new PathBuilder().redirect().join(Path.ITEM).input().join(registed.getId()).build();
	}

	@GetMapping(value = {"/detail/{id}"})
	public String detail(@PathVariable(required = true) Long id, Model model) {

		itemReopsitory.findById(id).ifPresent(info -> {
			model.addAttribute(FORM_PARAM, info);
		});
		return new PathBuilder().join(Path.ITEM).detial().build();
	}

	@GetMapping(value = {"/delete/{id}"})
	public String delete(@PathVariable(required = true) Long id, Model model,
	                     RedirectAttributes attrs) {

		if (itemService.delete(id)) {
			attrs.addAttribute(SUCCESS_MESSAGE, "削除しました。");
		}
		else {
			attrs.addAttribute("error", "処理に失敗しました。");
		}
		return new PathBuilder().redirect().join(Path.ITEM).index().build();
	}

}
