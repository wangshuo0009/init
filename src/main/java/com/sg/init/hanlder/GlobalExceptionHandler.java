package com.sg.init.hanlder;

import com.sg.init.exception.NotLoggedInException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NotLoggedInException.class)
    public ResponseEntity<String> handleNotLoggedIn(NotLoggedInException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
    }
    /**
     * 返回登陆页面
     */
    //@ExceptionHandler(NotLoggedInException.class)
    //public ModelAndView handleNotLoggedIn(NotLoggedInException e) {
    //    ModelAndView modelAndView = new ModelAndView();
    //    modelAndView.addObject("errorMessage", e.getMessage());
    //    modelAndView.setViewName("redirect:/index.html");
    //    return modelAndView;
    //}
}
