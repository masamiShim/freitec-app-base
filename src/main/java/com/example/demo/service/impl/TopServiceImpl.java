package com.example.demo.service.impl;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.db.repos.InformationRepository;
import com.example.demo.models.Information;
import com.example.demo.service.TopService;

@Service
public class TopServiceImpl implements TopService {

  @Autowired
  private InformationRepository informationRepository;

  @Override
  public List<Information> getDispInformationList() {
    return informationRepository
        .findByDateFromToAndDeletedFalse(LocalDate.now())
        .orElse(Collections.emptyList());
  }

}
