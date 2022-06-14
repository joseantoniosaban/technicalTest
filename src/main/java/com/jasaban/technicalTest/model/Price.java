package com.jasaban.technicalTest.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Price implements Serializable {

  private static final long serialVersionUID = 1763659892322894039L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private int brandId;

  @Column(name = "START_DATE", columnDefinition = "TIMESTAMP")
  @DateTimeFormat(pattern = "yyy-MM-dd-HH.mm.ss")
  private LocalDateTime startDate;

  @Column(name = "END_DATE", columnDefinition = "TIMESTAMP")
  @DateTimeFormat(pattern = "yyy-MM-dd-HH.mm.ss")
  private LocalDateTime endDate;

  @Column(name = "PRICE_LIST")
  private int fare;

  private int productId;
  private int priority;
  private double price;

  @Column(name = "CURR")
  private String currency;
}
