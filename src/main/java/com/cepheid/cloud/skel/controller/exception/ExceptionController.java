package com.cepheid.cloud.skel.controller.exception;

import com.cepheid.cloud.skel.exceptions.CreateException;
import com.cepheid.cloud.skel.exceptions.UpdateException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionController {

    @ResponseBody
    @ExceptionHandler(CreateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String createExceptionHandler(CreateException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(UpdateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String updateExceptionHandler(UpdateException ex) {
        return ex.getMessage();
    }
}
