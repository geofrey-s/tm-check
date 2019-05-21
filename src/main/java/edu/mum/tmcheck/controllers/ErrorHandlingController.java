package edu.mum.tmcheck.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;

@Controller
public class ErrorHandlingController implements ErrorController {
    @GetMapping("/error")
    public String handleError(HttpServletResponse response){
        if(response.getStatus() == HttpStatus.NOT_FOUND.value()){
            return "404Error";
        }
        else if(response.getStatus() == HttpStatus.FORBIDDEN.value()){
            return "403Error";
        }
        else if(response.getStatus() == HttpStatus.INTERNAL_SERVER_ERROR.value()){
            return "505Error";
        }
        else{
            return "error";
        }
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
