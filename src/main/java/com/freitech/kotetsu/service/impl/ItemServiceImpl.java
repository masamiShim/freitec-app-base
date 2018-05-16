package com.freitech.kotetsu.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;

import com.freitech.kotetsu.db.repos.ItemRepository;
import com.freitech.kotetsu.db.repos.specifications.InformationSpecification;
import com.freitech.kotetsu.exceptions.BussinessException;
import com.freitech.kotetsu.forms.item.ItemSearchForm;
import com.freitech.kotetsu.forms.system.information.InformationSearchForm;
import com.freitech.kotetsu.models.item.Item;
import com.freitech.kotetsu.models.item.ItemListModel;
import com.freitech.kotetsu.service.ItemService;

import lombok.Data;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	ItemSpecification spec;

	@Autowired
	ItemRepository itemRepository;

	@Override
	public Item regist(Item form) {
		return itemRepository.save(form);
	}

	@Override
	public boolean delete(Long id) {
		return itemRepository.findById(id).map(exists -> {
			exists.setDeleted(LocalDateTime.now());
			return itemRepository.save(exists) != null ? true : false;
		}).orElseThrow(BussinessException::new);

	}

	@Override
	public Item update(Long id, Item form) {
		return itemRepository.findById(id).map(exists -> {
			exists.setPersistInfo(form);
			return itemRepository.save(exists);
		}).orElseThrow(BussinessException::new);
	}

	@Override
	public List<Item> getItemList(ItemSearchForm form) {
		return itemRepository.findAll(Specifications
		  .where(spec.likeHeadItemNo(form.getItemNo()))
		  .and(spec.likeName(form.getName()))
		  .and(spec.equalsType(form.getType()))
		  .and(spec.deleted(false)));

	}
}
