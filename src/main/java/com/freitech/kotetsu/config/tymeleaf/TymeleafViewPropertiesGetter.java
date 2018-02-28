package com.freitech.kotetsu.config.tymeleaf;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

@Configuration(value = "pg")
public class TymeleafViewPropertiesGetter implements InitializingBean {

  private final PropertySourcesPlaceholderConfigurer conf = new PropertySourcesPlaceholderConfigurer();
  private final String YML_SOURCE = "view.yml";

  public String get(String val) {
    if (!conf.getAppliedPropertySources().get(YML_SOURCE)
        .containsProperty(val)) {
      return "";
    }
    return String.valueOf(
        conf.getAppliedPropertySources().get(YML_SOURCE).getProperty(val));
  }

  @Override
  public void afterPropertiesSet() throws Exception {
    YamlPropertiesFactoryBean yml = new YamlPropertiesFactoryBean();
    yml.setResources(new ClassPathResource(YML_SOURCE));
    conf.setProperties(yml.getObject());
  }
}
