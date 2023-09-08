package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;

import java.util.Arrays;
import java.util.List;


/*
    This Class demonstrates how a Server-side Request Forgery vulnerability can
    be manifested in a Spring application. A prevention technique is also used
    to show how the vulnerability can be remediated.
 */
@RestController
@RequestMapping("/ssrf")
public class RestControllerSSRF {

    @Autowired
    RestTemplate restTemplate;

    /*
        Server-side Request Forgery (SSRF) Vulnerability

        The application is using the request parameter called "url" to make
        an HTTP request, without any validations on the parameter.

        An attacker can perform a SSRF attack by injecting any arbitrary domain
        within the "url" parameter. This can allow an attacker to reach any internal
        system that they otherwise would not be able to reach, since this attack abuses
        the "trust" or network boundary the application has on other systems.

        To prevent SSRF, the call to the validate() method is used which will
        return a "true" value if the submitted data matches one of the domains
        listed in an Array. Only the data that passes this validation is passed to
        the functionality that initiates an HTTP request.
     */
    @RequestMapping(value = "ssrfTest")
    public String getData(@RequestParam("url") String url) {

        HttpHeaders headers = new HttpHeaders();

        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<String>(headers);

        try {

            // The "url" parameter is passed to the validate() method for validation
            boolean x = validate(url);

            // If the "url" parameter matched one of the URLs then the workflow in the if() statement will execute
            if(x) {

                // This is the method used to initiate an HTTP request
                return restTemplate.exchange(url, HttpMethod.GET, entity, String.class).getBody();

            }  // end if

        } catch(Exception e) {

            return "An error occurred.";

        }  // end try/catch

        return "An error occurred.";

    }  // end method




    /*
        This method returns a boolean value that is determined by whether the
        String url parameter matches one of the values in the "allowedURLs" Array.

        If the "url" parameter matches one of those values then the method
        returns the boolean value of true.
     */
    public boolean validate(String url) {

        // An Array of type String which will contain elements of URLs
        String[] allowedURLs = {"https://secure-domain-example", "https://secure-domain-example"};

        List<String> urlList = Arrays.asList(allowedURLs);

        // Returns a boolean value depending on if the "url" data matches one of the values in the "urlList"
        // Returns a true if it matches; Returns a false if it doesn't match
        return urlList.contains(url);

    }  // end method


}  // end Class
