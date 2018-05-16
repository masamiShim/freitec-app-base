package com.freitech.kotetsu.controllers.system.information;

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
import com.freitech.kotetsu.db.repos.InformationRepository;
import com.freitech.kotetsu.models.information.Information;
import com.freitech.kotetsu.service.InformationService;

@Controller
@RequestMapping("/information")
public class InformationController extends SpringControllerBase2<Information> {

	@Autowired
	private InformationService informationService;

	@Autowired
	private InformationRepository informationReopsitory;

	@ModelAttribute(name = FORM_PARAM)
	public Information getForm() {
		return new Information();
	}

	@GetMapping(value = {"/input", "/input/{id}"})
	public String input(@PathVariable(required = false) Optional<Long> id, Model model) {
		id.ifPresent(i -> {
			informationReopsitory.findById(i).ifPresent(info -> {
				model.addAttribute(FORM_PARAM, info);
			});

		});

		return new PathBuilder().join(Path.INFORMATION).input().build();
	}

	@PostMapping(value = {"/input", "/input/{id}"})
	public String confirm(@Valid @ModelAttribute("form") Information form, BindingResult error,
	                      @PathVariable(required = false) Optional<Long> id, Model model) {

		model.addAttribute(FORM_PARAM, form);
		// エラーあればさよなら
		if (error.hasErrors()) {
			return new PathBuilder().join(Path.INFORMATION).input().build();
		}
		return new PathBuilder().join(Path.INFORMATION).confirm().build();
	}

	@PostMapping(value = {"/complete", "/complete/{id}"})
	@Transactional
	public String complete(@Valid @ModelAttribute("form") Information form, BindingResult result,
	                       @PathVariable(required = false) Optional<Long> id, Model model,
	                       RedirectAttributes attrs) {
		// エラーあればさよなら
		if (result.hasErrors()) {
			model.addAttribute("form", form);
			return new PathBuilder().join(Path.INFORMATION).input().build();
		}

		Information registed = informationService.regist(form);
		if (null == registed) {
			result.addError(new ObjectError("error", "登録/更新に失敗しました。再度お試しください。"));
			return new PathBuilder().join(Path.INFORMATION).input().build();
		}

		model.addAttribute("success", "登録/更新しました。");
		return new PathBuilder().redirect().join(Path.INFORMATION).input().join(registed.getId()).build();
	}

	@GetMapping(value = {"/detail/{id}"})
	public String detail(@PathVariable(required = true) Optional<Long> id, Model model) {

		id.ifPresent(i -> {
			informationReopsitory.findById(i).ifPresent(info -> {
				model.addAttribute(FORM_PARAM, info);
			});
		});
		return new PathBuilder().join(Path.INFORMATION).detial().build();
	}

	@GetMapping(value = {"/delete/{id}"})
	public String delete(@PathVariable(required = true) Optional<Long> id, Model model,
	                     RedirectAttributes attrs) {
		id.ifPresent(i -> {
			if (informationService.delete(i)) {
				attrs.addAttribute(SUCCESS_MESSAGE, "削除しました。");
			}
			else {
				attrs.addAttribute("error", "処理に失敗しました。");
			}
		});
		return new PathBuilder().redirect().join(Path.INFORMATION).index().build();
	}

}
