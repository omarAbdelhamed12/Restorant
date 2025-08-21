package com.spring.boot.config;

import com.spring.boot.controller.vm.ExceptionResponseVm;
import com.spring.boot.dto.BundleMessage.BundleMessage;
import com.spring.boot.dto.ExceptionDto;
import com.spring.boot.exception.CustomSystemException;
import com.spring.boot.service.bandleService.BandleTranslatorService;
import jakarta.transaction.SystemException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;

import java.util.List;

@ControllerAdvice
public class ExceptionConfig {

    @Autowired
    private BandleTranslatorService bundleTranslatorService;

//    @ExceptionHandler(CustomSystemException.class)
//    public ResponseEntity<ExceptionResponseVm> handleSystemException(CustomSystemException exception) {
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
//                new ExceptionDto(
//                        BandleTranslatorService.getBundleMessageInEnglishAndArabic(exception.getMessage())
//                ));
//    }

    @ExceptionHandler(CustomSystemException.class)
    public ResponseEntity<ExceptionDto> handleSystemException(CustomSystemException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ExceptionDto(
                        bundleTranslatorService.getBundleMessageInEnglishAndArabic(exception.getMessage())
                )
        );
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponseVm> handleValidationExceptions(MethodArgumentNotValidException ex) {

        ExceptionResponseVm exceptions = new ExceptionResponseVm(new ArrayList<>());

        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            try {
                exceptions.getErrorMessages().add(bundleTranslatorService.getBundleMessageInEnglishAndArabic(error.getDefaultMessage()));
            } catch (Exception exception) {
                exceptions.getErrorMessages().add(new BundleMessage(error.getDefaultMessage(), null));
            }

        }

        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(exceptions);
    }
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ExceptionResponseVm> handleConstraintViolation(ConstraintViolationException ex) {
        ExceptionResponseVm exceptions = new ExceptionResponseVm(new ArrayList<>());

        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            try {
                exceptions.getErrorMessages().add(bundleTranslatorService.getBundleMessageInEnglishAndArabic(violation.getMessage()));
            } catch (Exception exception) {
                exceptions.getErrorMessages().add(new BundleMessage(violation.getMessage(), null));
            }
        }

        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(exceptions);
    }
}
