package com.inditex.technicalTest.exceptions;

public class ExpectationException extends ApiException {

  private static final String TITLE = "Expectation failed";
  private static final String ERROR_CODE = "001";

  public ExpectationException(final String description) {
    super(ERROR_CODE, TITLE, description);
  }
}
