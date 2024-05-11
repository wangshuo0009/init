package com.sg.bjftviewprotect.hanlder;

import com.sg.bjftviewprotect.exception.NotLoggedInException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NotLoggedInException.class)
    public ResponseEntity<String> handleNotLoggedIn(NotLoggedInException e) {
        return new ResponseEntity<>(e.getMessage(),HttpStatus.UNAUTHORIZED);
    }
}
