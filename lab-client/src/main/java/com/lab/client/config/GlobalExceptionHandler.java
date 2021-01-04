package com.lab.client.config;

import com.lab.client.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException.class)
    public R runtimeException(RuntimeException e) {
        Map<String, Object> body = new LinkedHashMap<String, Object>();
        body.put("timestamp", new Date());

        return R.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()).put("data", body);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public R handleBindGetException(MethodArgumentNotValidException ex) {
        Map<String, Object> body = new LinkedHashMap<String, Object>();
        body.put("timestamp", new Date());

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getDefaultMessage())
                .collect(Collectors.toList());
        body.put("errors", errors);

        return R.error(HttpStatus.BAD_REQUEST.value(), "数据校验失败").put("data", body);
    }

    @ExceptionHandler(value = ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public R handleBindException(ValidationException ex) {
        Map<String, Object> body = new LinkedHashMap<String, Object>();
        body.put("timestamp", new Date());

        List<String> errors = new LinkedList<String>();
        if (ex instanceof ConstraintViolationException) {
            ConstraintViolationException exs = (ConstraintViolationException) ex;
            Set<ConstraintViolation<?>> violations = exs.getConstraintViolations();
            for (ConstraintViolation<?> item : violations) {
                errors.add(item.getMessage());
            }
        }
        body.put("errors", errors);
        return R.error(HttpStatus.BAD_REQUEST.value(), "数据校验失败").put("data", body);
    }

    /**
     *
     * spring.mvc.throw-exception-if-no-handler-found: true
     * spring.resources.add-mappings: false
     *
     */
    @ExceptionHandler(value = NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public R handleNotFoundException(NoHandlerFoundException ex) {
        Map<String, Object> body = new LinkedHashMap<String, Object>();
        body.put("timestamp", new Date());

        return R.error(HttpStatus.NOT_FOUND.value(), "404-404-404").put("data", body);
    }
}
