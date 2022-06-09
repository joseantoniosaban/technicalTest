package com.inditex.technicalTest.exceptions;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ApiException extends RuntimeException {

  private static final long serialVersionUID = -5342545600732754203L;

  private final String code;
  private final String title;
  private final String detail;
}
