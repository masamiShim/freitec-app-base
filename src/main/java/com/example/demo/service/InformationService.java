package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.forms.system.information.InformationForm;
import com.example.demo.models.Information;

public interface InformationService {

  /**
   * お知らせの一覧を取得する。
   * 
   * @return
   */
  List<Information> getInformationList();

  /**
   * 登録処理
   * 
   * @param form
   * @return
   */
  Optional<Information> regist(InformationForm form);

  /**
   * 更新処理
   * 
   * @param form
   * @return
   */
  Information update(Long id, InformationForm form);

  void delete(Long id);

}
