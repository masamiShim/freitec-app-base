package com.example.demo.config.tymeleaf;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Configuration;

@Configuration(value="vsf")
public class TymeleafViewStringFormatter {

  SimpleDateFormat formatter = (SimpleDateFormat)DateFormat.getDateInstance();
  
  private String F_J_YMD = "YYYY年MM月DD日";
  private String F_J_YM = "YYYY年MM月";
  private String F_YMD = "YYYY/MM/DD";
  private String F_YM = "YYYY/MM";
  
  public String formatYMDWithSlash(String target){
    if(StringUtils.isEmpty(target)){
      return target;
    }
    return target;
//    return new LocalDate().parse(target).format(F_J_YM);
  }
}
