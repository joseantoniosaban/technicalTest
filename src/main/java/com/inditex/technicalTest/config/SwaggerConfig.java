package com.inditex.technicalTest.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class SwaggerConfig {

  private final SwaggerEnvConfiguration swaggerEnvConfiguration;

  @Bean
  public OpenAPI api() {
    return new OpenAPI().info(metaData());
  }

  private Info metaData() {

    String title = swaggerEnvConfiguration.getTitle();
    String description = swaggerEnvConfiguration.getDescription();
    String version = swaggerEnvConfiguration.getVersion();
    String contactName = swaggerEnvConfiguration.getContactName();
    String contactUrl = swaggerEnvConfiguration.getContactUrl();
    String contactEmail = swaggerEnvConfiguration.getContactEmail();

    final Contact contact = new Contact().email(contactEmail).name(contactName).url(contactUrl);

    return new Info().title(title).description(description).version(version).contact(contact);
  }
}
