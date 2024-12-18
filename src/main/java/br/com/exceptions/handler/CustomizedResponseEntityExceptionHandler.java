package br.com.exceptions.handler;

import br.com.exceptions.ExceptionResponse;
import br.com.exceptions.InvalidJwtAuthenticationException;
import br.com.exceptions.RequiredObjectIsNullException;
import br.com.exceptions.ResourceNotFoundException;
import java.util.Date;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(Exception.class)
  public final ResponseEntity<ExceptionResponse> handleAllExceptions(
      Exception ex, WebRequest request) {

    ExceptionResponse exceptionResponse = new ExceptionResponse(
        new Date(),
        ex.getMessage(),
        request.getDescription(false));

    return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(ResourceNotFoundException.class)
  public final ResponseEntity<ExceptionResponse> handleNotFoundExceptions(
      Exception ex, WebRequest request) {

    ExceptionResponse exceptionResponse = new ExceptionResponse(
        new Date(),
        ex.getMessage(),
        request.getDescription(false));

    return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(RequiredObjectIsNullException.class)
  public final ResponseEntity<ExceptionResponse> handleRequiredObjectIsNullException(
      Exception ex, WebRequest request) {

    ExceptionResponse exceptionResponse = new ExceptionResponse(
        new Date(),
        ex.getMessage(),
        request.getDescription(false));

    return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(InvalidJwtAuthenticationException.class)
  public final ResponseEntity<ExceptionResponse> handleInvalidJwtAuthenticationException(
      Exception ex, WebRequest request) {

    ExceptionResponse exceptionResponse = new ExceptionResponse(
        new Date(),
        ex.getMessage(),
        request.getDescription(false));

    return new ResponseEntity<>(exceptionResponse, HttpStatus.FORBIDDEN);
  }

}
