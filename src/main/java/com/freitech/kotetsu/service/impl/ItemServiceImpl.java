package com.freitech.kotetsu.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;

import com.freitech.kotetsu.db.repositories.ItemRepository;
import com.freitech.kotetsu.db.specifications.ItemSpecification;
import com.freitech.kotetsu.exceptions.BussinessException;
import com.freitech.kotetsu.forms.item.ItemSearchForm;
import com.freitech.kotetsu.models.item.Item;
import com.freitech.kotetsu.service.ItemService;

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
