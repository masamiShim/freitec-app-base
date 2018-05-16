package com.freitech.kotetsu.controllers.estimate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.freitech.kotetsu.codes.SectionType;
import com.freitech.kotetsu.config.setting.Path;
import com.freitech.kotetsu.config.setting.PathBuilder;
import com.freitech.kotetsu.controllers.commons.SpringControllerBase2;
import com.freitech.kotetsu.db.repos.EstimateRepository;
import com.freitech.kotetsu.models.estimate.Estimate;
import com.freitech.kotetsu.models.estimate.EstimateDetail;
import com.freitech.kotetsu.service.EstimateService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/estimate")
public class EstimateController extends SpringControllerBase2<Estimate> {

	@Autowired
	EstimateService estimateService;

	@Autowired
	EstimateRepository estimateRepository;

	private ArrayList<EstimateDetail> details;

	@ModelAttribute(name = FORM_PARAM)
	public Estimate getForm() {
		return new Estimate();
	}

	@ModelAttribute(name = "details")
	public List<EstimateDetail> getDetail() {
		return details;
	}

	@ModelAttribute(name = "sections")
	public List<SectionType> getSectionTypes() {
		return Arrays.asList(SectionType.values());
	}

	/**
	 * 入力画面初期表示
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping(value = {"/input", "/input/{id}"})
	public String input(@PathVariable(required = false) Optional<Long> id, Model model) {
		id.ifPresent(i -> {
			estimateRepository.findById(i).ifPresent(info -> {
				model.addAttribute(FORM_PARAM, info);
			});

		});

		return new PathBuilder().join(Path.ESTIMATE).input().build();
	}

	/**
	 * 明細の追加
	 * @param form
	 * @param bindingResult
	 * @param id
	 * @param model
	 * @return
	 */
	@PostMapping(value = {"/input", "/input/{id}"}, params = "add")
	public String addList(@ModelAttribute("form") Estimate form,
	                      BindingResult bindingResult,
	                      @PathVariable(required = false) Optional<Long> id, Model model) {

		if (CollectionUtils.isEmpty(form.getDetails())) {
			form.setDetails(new ArrayList<>());
		}

		form.getDetails().add(new EstimateDetail());
		model.addAttribute(FORM_PARAM, form);

		return new PathBuilder().join(Path.ESTIMATE).input().build();
	}

	/**
	 * 確認画面遷移
	 * 
	 * @param form
	 * @param error
	 * @param id
	 * @param model
	 * @return
	 */
	@PostMapping(value = {"/input", "/input/{id}"}, params = "confirm")
	public String confirm(@Validated @ModelAttribute("form") Estimate form, BindingResult error,
	                      @PathVariable(required = false) Optional<Long> id, Model model) {

		model.addAttribute(FORM_PARAM, form);
		// エラーあればさよなら
		if (error.hasErrors()) {
			return new PathBuilder().join(Path.ESTIMATE).input().build();
		}
		return new PathBuilder().join(Path.ESTIMATE).confirm().build();
	}

	/**
	 * 完了処理
	 * @param form
	 * @param result
	 * @param id
	 * @param model
	 * @param attrs
	 * @return
	 */
	@PostMapping(value = {"/complete", "/complete/{id}"})
	@Transactional
	public String complete(@Validated @ModelAttribute("form") Estimate form, BindingResult result,
	                       @PathVariable(required = false) Optional<Long> id, Model model,
	                       RedirectAttributes attrs) {
		// エラーあればさよなら
		if (result.hasErrors()) {
			model.addAttribute("form", form);
			return new PathBuilder().join(Path.ESTIMATE).input().build();
		}

		return estimateService.regist(form).map(r -> {
			model.addAttribute("success", "登録/更新しました。");
			return new PathBuilder().redirect().join(Path.ESTIMATE).input().join(r.getId()).build();
		}).orElseGet(() -> {
			result.addError(new ObjectError("error", "登録/更新に失敗しました。再度お試しください。"));
			return new PathBuilder().join(Path.ESTIMATE).input().build();
		});
	}

	/**
	 * 詳細表示
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping(value = {"/detail/{id}"})
	public String detail(@PathVariable(required = true) Optional<Long> id, Model model) {

		id.ifPresent(i -> {
			estimateRepository.findById(i).ifPresent(info -> {
				model.addAttribute(FORM_PARAM, info);
			});
		});
		return new PathBuilder().join(Path.ESTIMATE).detial().build();
	}

	@GetMapping(value = {"/delete/{id}"})
	public String delete(@PathVariable(required = true) Optional<Long> id, Model model,
	                     RedirectAttributes attrs) {
		id.ifPresent(i -> {
			if (estimateService.delete(i)) {
				attrs.addAttribute(SUCCESS_MESSAGE, "削除しました。");
			}
			else {
				attrs.addAttribute("error", "処理に失敗しました。");
			}
		});
		return new PathBuilder().redirect().join(Path.ESTIMATE).index().build();
	}

	/**
	 * 明細欄の削除
	 * 
	 * @param id
	 * @param model
	 * @param attrs
	 * @return
	 */
	@GetMapping(value = {"/detail/delete/{id}"})
	public String deleteDetail(@PathVariable(required = true) Optional<Integer> id, Model model,
	                           RedirectAttributes attrs) {
		id.ifPresent(i -> {
			details.remove(i.intValue());
		});
		return new PathBuilder().redirect().join(Path.ESTIMATE).input().build();
	}

}
