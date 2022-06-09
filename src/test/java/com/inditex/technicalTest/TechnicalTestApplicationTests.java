package com.inditex.technicalTest;

import static org.assertj.core.api.Assertions.assertThat;

import com.inditex.technicalTest.config.PathConfig;
import com.inditex.technicalTest.controller.PriceController;
import com.inditex.technicalTest.dto.Price;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@EnableConfigurationProperties(value = PathConfig.class)
class TechnicalTestApplicationTests {

  private static final String PATH_MAPPING = "/technicalTest/price?date=2020-06-14-10.00.00&productId=35453&brandId=1";
  @Autowired
  private PriceController priceController;
  @Autowired
  private TestRestTemplate restTemplate;
  @Autowired
  private PathConfig pathConfig;
  @LocalServerPort
  private int port;

  @Test
  void contextLoads() {
    assertThat(priceController).isNotNull();
  }

  @Test
  void givenTestServerInRandomPort_whenGetPrice_thenResponds() {

    var response = restTemplate.getForObject(
        "http://localhost:" + port + pathConfig.getContextPath() + PATH_MAPPING, Price.class);

    assertThat(response).isNotNull();
  }
}
