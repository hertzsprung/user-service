package uk.co.datumedge.bpdts.rest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

/**
 * Return <code>BAD_REQUEST</code> status for <code>ConstraintViolationException</code>s.
 *
 * @see <a href="https://github.com/eugenp/tutorials/blob/master/spring-mvc-xml/src/main/java/com/baeldung/spring/controller/ConstraintViolationExceptionHandler.java">github.com/eugenp/tutorials</a>
 */
@ControllerAdvice
public class ConstraintViolationExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {ConstraintViolationException.class})
    protected ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException e, WebRequest request) {
        return handleExceptionInternal(e, e.getMessage(), new HttpHeaders(), BAD_REQUEST, request);
    }
}
