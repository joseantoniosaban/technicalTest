package com.jasaban.technicalTest.exceptions;

public class DataNotFoundException extends ApiException {
  private static final String TITLE = "Data not found";
  private static final String ERROR_CODE = "002";

  public DataNotFoundException(final String description) {
    super(ERROR_CODE, TITLE, description);
  }
}
