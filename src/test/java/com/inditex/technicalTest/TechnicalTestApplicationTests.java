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

  private static final String PATH_MAPPING =
      "/technicalTest/price?date=2020-06-14-10.00.00&productId=35455&brandId=1";
  private static final String PATH_MAPPING_TEST1 =
      "/technicalTest/price?date=2020-06-14-10.00.00&productId=35455&brandId=1";
  private static final String PATH_MAPPING_TEST2 =
      "/technicalTest/price?date=2020-06-14-16.00.00&productId=35455&brandId=1";
  private static final String PATH_MAPPING_TEST3 =
      "/technicalTest/priceStream?date=2020-06-14-21.00.00&productId=35455&brandId=1";
  private static final String PATH_MAPPING_TEST4 =
      "/technicalTest/priceStream?date=2020-06-15-10.00.00&productId=35455&brandId=1";
  private static final String PATH_MAPPING_TEST5 =
      "/technicalTest/priceStream?date=2020-06-16-21.00.00&productId=35455&brandId=1";
  @Autowired private PriceController priceController;
  @Autowired private TestRestTemplate restTemplate;
  @Autowired private PathConfig pathConfig;
  @LocalServerPort private int port;

  @Test
  void contextLoads() {
    assertThat(priceController).isNotNull();
  }

  @Test
  void givenTestServerInRandomPort_whenGetPrice_thenResponds() {

    var response =
        restTemplate.getForObject(
            "http://localhost:" + port + pathConfig.getContextPath() + PATH_MAPPING, Price.class);

    assertThat(response).isNotNull();
  }

  @Test
  void givenTest1_whenGetPrice_thenResponds() {

    var response =
        restTemplate.getForObject(
            "http://localhost:" + port + pathConfig.getContextPath() + PATH_MAPPING_TEST1,
            Price.class);
    assertThat(response.getPrice()).isEqualTo(35.5);
    assertThat(response.getPriority()).isEqualTo(0);
    assertThat(response.getFare()).isEqualTo(1);
  }

  @Test
  void givenTest2_whenGetPrice_thenResponds() {

    var response =
        restTemplate.getForObject(
            "http://localhost:" + port + pathConfig.getContextPath() + PATH_MAPPING_TEST2,
            Price.class);
    assertThat(response.getPrice()).isEqualTo(25.45);
    assertThat(response.getPriority()).isEqualTo(1);
    assertThat(response.getFare()).isEqualTo(2);
  }

  @Test
  void givenTest3_whenGetPrice_thenResponds() {

    var response =
        restTemplate.getForObject(
            "http://localhost:" + port + pathConfig.getContextPath() + PATH_MAPPING_TEST3,
            Price.class);
    assertThat(response.getPrice()).isEqualTo(35.5);
    assertThat(response.getPriority()).isEqualTo(0);
    assertThat(response.getFare()).isEqualTo(1);
  }

  @Test
  void givenTest4_whenGetPrice_thenResponds() {

    var response =
        restTemplate.getForObject(
            "http://localhost:" + port + pathConfig.getContextPath() + PATH_MAPPING_TEST4,
            Price.class);
    assertThat(response.getPrice()).isEqualTo(30.5);
    assertThat(response.getPriority()).isEqualTo(1);
    assertThat(response.getFare()).isEqualTo(3);
  }

  @Test
  void givenTest5_whenGetPrice_thenResponds() {

    var response =
        restTemplate.getForObject(
            "http://localhost:" + port + pathConfig.getContextPath() + PATH_MAPPING_TEST5,
            Price.class);
    assertThat(response.getPrice()).isEqualTo(38.95);
    assertThat(response.getPriority()).isEqualTo(1);
    assertThat(response.getFare()).isEqualTo(4);
  }
}
