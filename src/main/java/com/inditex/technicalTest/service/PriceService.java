package com.inditex.technicalTest.service;


import com.inditex.technicalTest.dto.Price;
import com.inditex.technicalTest.exceptions.DataNotFoundException;
import com.inditex.technicalTest.exceptions.ExpectationException;
import com.inditex.technicalTest.repository.PriceRepository;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PriceService {

  @Autowired private PriceRepository priceRepository;

  public Price getPrice(String date, int productId, int brandId) {
    try {
      var response = priceRepository.findByQueryFilter(brandId, productId, date);
      if (response.isEmpty()) {
        throw new ExpectationException(
            "No data found for productId: "
                + productId
                + " and brandId: "
                + brandId
                + " and date: "
                + date);
      } else {
        return Price.builder()
            .brandId(response.get(0).getBrandId())
            .productId(response.get(0).getProductId())
            .fare(response.get(0).getFare())
            .startDate(response.get(0).getStartDate().toString())
            .endDate(response.get(0).getEndDate().toString())
            .price(response.get(0).getPrice())
            .priority(response.get(0).getPriority())
            .currency(response.get(0).getCurrency())
            .build();
      }
    } catch (Exception e) {
      throw new ExpectationException(e.getMessage());
    }
  }

  public Price findPrice(int productId, int brandId, String date) {

    try {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyy-MM-dd-HH.mm.ss");
      LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
      var response =
          priceRepository.findByProductIdAndBrandIdOrderByPriorityDesc(productId, brandId);
      if (response.isEmpty()) {
        throw getDataNotFoundException(productId, brandId, dateTime);
      } else {
        return response.stream()
            .filter(p -> p.getStartDate().isBefore(dateTime) && p.getEndDate().isAfter(dateTime))
            .findFirst()
            .map(
                p ->
                    Price.builder()
                        .brandId(p.getBrandId())
                        .productId(p.getProductId())
                        .fare(p.getFare())
                        .startDate(p.getStartDate().toString())
                        .endDate(p.getEndDate().toString())
                        .price(p.getPrice())
                        .currency(p.getCurrency())
                        .priority(p.getPriority())
                        .build())
            .orElseThrow(() -> getDataNotFoundException(productId, brandId, dateTime));
      }
    } catch (DataNotFoundException e) {
      throw e;
    } catch (Exception e) {
      throw new ExpectationException(e.getMessage());
    }
  }

  private DataNotFoundException getDataNotFoundException(
      int productId, int brandId, LocalDateTime dateTime) {
    return new DataNotFoundException(
        "No data found for productId: "
            + productId
            + " and brandId: "
            + brandId
            + " and date: "
            + dateTime);
  }
}
