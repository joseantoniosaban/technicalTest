package com.jasaban.technicalTest.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Price implements Serializable {

  private static final long serialVersionUID = -4764732925542799624L;
  private int brandId;
  private String startDate;
  private String endDate;
  private int fare;
  private int productId;
  private int priority;
  private double price;
  private String currency;
}
