package dev.mikoto2000.springboot.validation.workshop.firststep.controller.advice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import dev.mikoto2000.springboot.validation.workshop.firststep.bean.ValidationError;
import dev.mikoto2000.springboot.validation.workshop.firststep.bean.ValidationErrors;
import lombok.extern.slf4j.Slf4j;

/**
 * ValidationExceptionHandler
 */
@Slf4j
@RestControllerAdvice
public class ValidationExceptionHandler {

  /**
   * メソッド引数の単純値のバリデーションエラーを処理するハンドラ。
   * @return ValidationErrors
   */
  @ExceptionHandler(HandlerMethodValidationException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ValidationErrors handleMethodValidationException(HandlerMethodValidationException e) {

    log.info("handleMethodValidationException: {}", e);

    List<ValidationError> errors = new ArrayList<>();

    for (var error : e.getParameterValidationResults()) {
      String field = error.getMethodParameter().getParameterName();

      List<ValidationError> fieldErrors = error.getResolvableErrors().stream()
          .map(ve -> new ValidationError(field, ve.getDefaultMessage()))
          .toList();

      errors.addAll(fieldErrors);
    }

    return new ValidationErrors(errors);
  }

  /**
   * メソッド引数の bean のバリデーションエラーを処理するハンドラ。
   * @return ValidationErrors
   */
  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ValidationErrors handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {

    log.info("handleMethodArgumentNotValidException: {}", e);

    List<ValidationError> errors = e.getFieldErrors().stream()
        .map((error) -> new ValidationError(error.getField(), error.getDefaultMessage()))
        .toList();

    return new ValidationErrors(errors);
  }

  /**
   * シンタックスエラーなど、そもそも正しくないリクエストだった場合に処理するハンドラ。
   * @return ValidationErrors
   */
  @ExceptionHandler(HttpMessageNotReadableException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ValidationErrors handleMethodArgumentNotValidException(HttpMessageNotReadableException e) {
    log.info("handleMethodArgumentNotValidException: {}", e);

    List<ValidationError> errors = List.of(new ValidationError(null, e.getLocalizedMessage()));

    return new ValidationErrors(errors);
  }
}
