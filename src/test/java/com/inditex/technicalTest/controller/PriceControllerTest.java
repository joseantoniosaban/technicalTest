package com.inditex.technicalTest.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.inditex.technicalTest.service.PriceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class PriceControllerTest {

  private static final String INCORRECT_PATH_MAPPING = "/technicalTest/brand";
  private static final String PATH_MAPPING =
      "/technicalTest/price?date=2020-06-14-10.00.00&productId=35453&brandId=1";
  private static final String PATH_MAPPING_PRICE_STREAM =
      "/technicalTest/priceStream?date=2020-06-14-10.00.00&productId=35453&brandId=1";

  @InjectMocks private PriceController priceController;

  @Mock private PriceService priceService;

  private MockMvc mockMvc;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    mockMvc = MockMvcBuilders.standaloneSetup(priceController).build();
  }

  @Test
  public void getPriceWithInvalidRequestShouldReturn404() throws Exception {
    mockMvc.perform(get(INCORRECT_PATH_MAPPING)).andExpect(status().isNotFound());
  }

  @Test
  public void getPriceWithInvalidPostRequestShouldReturn405() throws Exception {
    mockMvc.perform(post(PATH_MAPPING)).andExpect(status().isMethodNotAllowed());
  }

  @Test
  public void getPriceWhitValidRequestShouldReturn200() throws Exception {
    mockMvc.perform(get(PATH_MAPPING)).andExpect(status().isOk());
  }

  @Test
  public void findPriceWithInvalidPostRequestShouldReturn405() throws Exception {
    mockMvc.perform(post(PATH_MAPPING_PRICE_STREAM)).andExpect(status().isMethodNotAllowed());
  }

  @Test
  public void findPriceWhitValidRequestShouldReturn200() throws Exception {
    mockMvc.perform(get(PATH_MAPPING_PRICE_STREAM)).andExpect(status().isOk());
  }
}
