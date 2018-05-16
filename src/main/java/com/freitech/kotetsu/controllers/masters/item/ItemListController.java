package com.freitech.kotetsu.controllers.masters.item;

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
import com.freitech.kotetsu.codes.SectionType;
import com.freitech.kotetsu.codes.Unit;
import com.freitech.kotetsu.config.annotations.ColSize;
import com.freitech.kotetsu.config.annotations.ViewDef;
import com.freitech.kotetsu.config.setting.Path;
import com.freitech.kotetsu.controllers.commons.ListControllerBase;
import com.freitech.kotetsu.controllers.commons.SpringControllerBase2;
import com.freitech.kotetsu.forms.item.ItemSearchForm;
import com.freitech.kotetsu.forms.system.information.InformationSearchForm;
import com.freitech.kotetsu.service.InformationService;
import com.freitech.kotetsu.service.ItemService;

@Controller
@RequestMapping("/master/item")
@ViewDef(layout = ColSize.ONE, title = "お知らせ一覧")
public class ItemListController extends ListControllerBase<ItemSearchForm> {

	@Autowired
	private ItemService itemService;

	@Autowired
	private HttpSession session;

	@ModelAttribute(name = "types")
	public List<ItemType> getItemTypes() {
		return Arrays.asList(ItemType.values());
	}

	@ModelAttribute(name = "units")
	public List<Unit> getUnit() {
		return Arrays.asList(Unit.values());
	}

	@GetMapping(path = INDEX)
	public String index(Model model) {

		ItemSearchForm cond = searchCond();
		model.addAttribute("result", itemService.getItemList(cond));
		model.addAttribute("cond", cond);

		return Path.ITEM.getPath().concat(INDEX);
	}

	@PostMapping(path = INDEX)
	public String list(@Valid @ModelAttribute("cond") ItemSearchForm cond, BindingResult error,
	                   Model model) {
		// エラーあればさよなら
		if (error.hasErrors()) {
			createBindingErrorView(error, model);
			return Path.ITEM.getPath().concat(INDEX);
		}

		model.addAttribute("cond", cond);
		session.setAttribute("cond", cond);
		model.addAttribute("result", itemService.getItemList(cond));

		// TODO: 検索条件が必要なら
		return Path.ITEM.getPath().concat(INDEX);
	}

	protected ItemSearchForm load() {
		return new ItemSearchForm();
	}
}
