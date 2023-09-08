package com.example.demo.controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/*
    This Class implements default error messages to be sent to the client/browser.
    This prevents the default framework's error page from being sent, which
    prevents information disclosure.
 */
@Controller
public class MyErrorController implements ErrorController {

    @RequestMapping("/error")
    @ResponseBody
    public String handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());

            if(statusCode == HttpStatus.NOT_FOUND.value()) {
                return "Not Found";
            }
            else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                return "Internal Server Error";
            }
            else if(statusCode == HttpStatus.BAD_REQUEST.value()) {
                return "Bad Request";
            }
        }
        return "Error Occurred";
    }  // end method

}  // end Class
