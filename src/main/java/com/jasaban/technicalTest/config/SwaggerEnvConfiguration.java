package com.jasaban.technicalTest.config;


import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties("swagger")
class SwaggerEnvConfiguration {

  private String version;
  private String title;
  private String description;
  private Map<String, String> contact;

  String getContactName() {
    return contact.get("name");
  }

  String getContactUrl() {
    return contact.get("url");
  }

  String getContactEmail() {
    return contact.get("email");
  }
}
