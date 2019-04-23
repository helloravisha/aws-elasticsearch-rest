package com.ravisha.aws.elasticcache.rest.exception;

import com.ravisha.aws.elasticcache.rest.constants.ErrorConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

/**
 * Centralized handler responsible for capturing the exception.
 *
 * @author ravi Katta.
 */
@ControllerAdvice
public class EmployeeExceptionHandler extends ResponseEntityExceptionHandler {

  private final Logger log = LoggerFactory.getLogger(this.getClass());

  /**
   * Responsibe for handling all the employee exceptions.
   * @param employeeException
   * @param request
   * @return
   */
  @ExceptionHandler(EmployeeException.class)
  public final ResponseEntity<EmployeeException> handleUserExceptions(EmployeeException employeeException, WebRequest request) {
    return new ResponseEntity<>(employeeException, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
  }

  /**
   * Its predefined method overidden here to just capture any missing paramters in th given request.
   * @param ex
   * @param headers
   * @param status
   * @param request
   * @return
   */
  @Override
  protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
    String name = ex.getParameterName();
    return super.handleMissingServletRequestParameter(ex, headers, status, request);
  }


}