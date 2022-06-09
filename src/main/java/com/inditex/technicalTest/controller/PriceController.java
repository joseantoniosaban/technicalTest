package com.inditex.technicalTest.controller;

import static org.springframework.http.HttpStatus.EXPECTATION_FAILED;

import com.inditex.technicalTest.dto.ErrorResponse;
import com.inditex.technicalTest.dto.Price;
import com.inditex.technicalTest.exceptions.ExpectationException;
import com.inditex.technicalTest.service.PriceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(PriceController.BASE_URL)
@Controller
public class PriceController {

  public static final String BASE_URL = "/technicalTest";

  @Autowired private PriceService priceService;

  @GetMapping("/price")
  @Operation(summary = "Get Price")
  @ApiResponse(
      responseCode = "200",
      description = "This API returns searched price",
      content = {
        @Content(
            mediaType = "*/*",
            array = @ArraySchema(schema = @Schema(implementation = Price.class)))
      })
  @ApiResponse(
      responseCode = "400",
      description = "Bad Request",
      content = {
        @Content(
            mediaType = "*/*",
            array = @ArraySchema(schema = @Schema(implementation = ErrorResponse.class)))
      })
  @ApiResponse(
      responseCode = "404",
      description = "Not Found",
      content = {
        @Content(
            mediaType = "*/*",
            array = @ArraySchema(schema = @Schema(implementation = ErrorResponse.class)))
      })
  @ApiResponse(
      responseCode = "417",
      description = "Expectation Failed",
      content = {
        @Content(
            mediaType = "*/*",
            array = @ArraySchema(schema = @Schema(implementation = ErrorResponse.class)))
      })
  public ResponseEntity<Price> getPrice(
      @RequestParam("date") String date,
      @RequestParam("productId") int productId,
      @RequestParam("brandId") int brandId) {
    try {
      return new ResponseEntity<Price>(
          priceService.getPrice(date, productId, brandId), HttpStatus.OK);
    } catch (ExpectationException e) {
      throw e;
    }
  }

  @GetMapping("/priceStream")
  @Operation(summary = "Get Price")
  @ApiResponse(
      responseCode = "200",
      description = "This API returns searched price",
      content = {
        @Content(
            mediaType = "*/*",
            array = @ArraySchema(schema = @Schema(implementation = Price.class)))
      })
  @ApiResponse(
      responseCode = "400",
      description = "Bad Request",
      content = {
        @Content(
            mediaType = "*/*",
            array = @ArraySchema(schema = @Schema(implementation = ErrorResponse.class)))
      })
  @ApiResponse(
      responseCode = "404",
      description = "Not Found",
      content = {
        @Content(
            mediaType = "*/*",
            array = @ArraySchema(schema = @Schema(implementation = ErrorResponse.class)))
      })
  @ApiResponse(
      responseCode = "417",
      description = "Expectation Failed",
      content = {
        @Content(
            mediaType = "*/*",
            array = @ArraySchema(schema = @Schema(implementation = ErrorResponse.class)))
      })
  public ResponseEntity<Price> getPriceStream(
      @RequestParam("date") String date,
      @RequestParam("productId") int productId,
      @RequestParam("brandId") int brandId) {
    try {
      return new ResponseEntity<Price>(
          priceService.findPrice(productId, brandId, date), HttpStatus.OK);
    } catch (ExpectationException e) {
      throw e;
    }
  }

  @ExceptionHandler(ExpectationException.class)
  public ResponseEntity<ErrorResponse> handleApiException(ExpectationException ex) {
    return new ResponseEntity<>(
        ErrorResponse.builder()
            .code(ex.getCode())
            .detail(ex.getDetail())
            .title(ex.getTitle())
            .build(),
        EXPECTATION_FAILED);
  }
}
