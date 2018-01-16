package com.example.demo.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.db.repos.InformationRepository;
import com.example.demo.forms.system.information.InformationForm;
import com.example.demo.models.Information;
import com.example.demo.service.InformationService;

@Service
public class InformationServiceImpl implements InformationService {

  @Autowired
  private InformationRepository informationRepository;

  @Override
  public List<Information> getInformationList() {
    return null;
//        informationRepository.findByDeletedFalse()
//        .orElse(Collections.emptyList());
  }

  @Override
  public Optional<Information> regist(InformationForm form) {
    return Optional.ofNullable(informationRepository.save(new Information(
        form.getContent(), form.getStartDate(), form.getEndDate())));
  }

  @Override
  public Information update(Long id, InformationForm form) {
    Optional<Information> information = Optional.ofNullable(informationRepository.findOne(id));
    if(information.isPresent()){
      information.map(i -> {
        i.setContent(form.getContent());
        i.setStartDate(form.getStartDate());
        i.setEndDate(form.getEndDate());
        return informationRepository.save(information.get());
      });
    }
    return null;
  }

  @Override
  public void delete(Long id) {
    informationRepository.delete(id);
  }

}
