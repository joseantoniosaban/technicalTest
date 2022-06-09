package com.inditex.technicalTest.service;

import com.inditex.technicalTest.dto.Price;
import com.inditex.technicalTest.exceptions.ExpectationException;
import com.inditex.technicalTest.repository.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PriceService {

  @Autowired
  private PriceRepository priceRepository;

  public Price getPrice(String date, int productId, int brandId) {
    try {
      var response = priceRepository.findByProductId(brandId, productId, date);
      if (response.isEmpty()) {
        throw new ExpectationException(
            "No data found for productId: " + productId + " and brandId: " + brandId + " and date: "
                + date);
      } else {
        return Price.builder().brandId(response.get(0).getBrandId())
                    .productId(response.get(0).getProductId()).fare(response.get(0).getFare())
                    .startDate(response.get(0).getStartDate().toString()).endDate(response.get(0).getEndDate().toString())
                    .price(response.get(0).getPrice()).currency(response.get(0).getCurrency())
                    .build();
      }
    } catch (Exception e) {
      throw new ExpectationException(e.getMessage());
    }
  }
}
