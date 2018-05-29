package com.freitech.kotetsu.service.impl;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.freitech.kotetsu.db.repositories.InformationRepository;
import com.freitech.kotetsu.models.information.Information;
import com.freitech.kotetsu.service.TopService;

@Service
public class TopServiceImpl implements TopService {

	@Autowired
	private InformationRepository informationRepository;

	@Override
	public List<Information> getDispInformationList() {
		return informationRepository.findByCurrentDisplayInfo(LocalDate.now()).orElse(Collections.emptyList());
	}

}
