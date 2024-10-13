package br.com.exceptions;

import java.io.Serializable;
import java.util.Date;

public record ExceptionResponse(Date timestamp, String message, String details) implements
    Serializable {

  private static final long serialVersionUID = 1L;

}
