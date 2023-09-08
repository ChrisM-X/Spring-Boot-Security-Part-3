package com.example.demo.controllers;

import jakarta.validation.constraints.Pattern;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.owasp.encoder.Encode;  // required in order to use the Java Encoder library


/*
    This Class demonstrates how a Cross-site Scripting vulnerability can
    be manifested in a Spring application. Two prevention techniques are used
    to show how the vulnerability can be remediated.
 */
@Validated
@RestController
@Service
@RequestMapping("/xss")
public class RestControllerXSS {


    /*
        The xssWithInputValidation() method demonstrates how we can use validation annotations
        such as @Pattern() to implement input validation on the data fields before processing
        them within the application's business logic.

        IMPORTANT:  In order for any of the validation annotations used in request parameters
        or path variables to work properly, it is required to include the @Validated annotation at
        the Class level. (see above)

        In the example below, only alphanumeric and white space characters are allowed.
 */
    @GetMapping(value = "/xssTest", produces = "text/html")
    public ResponseEntity<String> xssWithInputValidation(@RequestParam("name") @Pattern(regexp = "[\\w\\d\\s]+")
                                                         String name) {

        return ResponseEntity.status(HttpStatus.OK)
                .body("<p> Hello, validated data " + name + "</p>" );

    } // end method




    /*
        The xssWithOutputEncoding() method demonstrates how we can use OWASP Java Encoder
        library to encode untrusted input before rendering the data in the HTTP response.

        Depending on the context of where the data will be appearing in the response, a
        different method will need to be used in order for this protection to work properly.

        The JavaDoc explains the different use cases for each of the methods:
            https://javadoc.io/doc/org.owasp.encoder/encoder/latest/index.html

        Below are examples of using the common implementations:  forHtml() and forJavaScript()

        Output encoding prevents the execution of malicious scripts as the browser will treat
        the injected data as data; and Not as code.
     */
    @GetMapping(value = "/xssTest2", produces = "text/html")
    public ResponseEntity<String> xssWithOutputEncoding(@RequestParam("name") String name) {

        return ResponseEntity.status(HttpStatus.OK)
                .body("<p> Hello, encoded data " + Encode.forHtml(name) + "</p>" );
                //.body("<script>  var x = ' " + Encode.forJavaScript(name) + " '; </script>");

    } // end method


} // end class
