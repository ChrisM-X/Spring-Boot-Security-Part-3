package com.example.demo.controllers;

import java.util.Objects;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import org.owasp.encoder.Encode;

import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;


import com.example.demo.models.User;
import com.example.demo.repositories.UserRepo;


/*
    This Class demonstrates how a Mass Assignment vulnerability can
    be manifested in a Spring web application. A prevention technique is also used
    to show how the vulnerability can be remediated.
 */
@Validated
@Controller
@Service
public class UserController {

    @Autowired
    private UserRepo repo;

    @Autowired
    private User userObject;


    /*
     * Prevent Mass Assignment vulnerability on this Web Application.
     *
     * This configuration is ensuring that only the id, username, and email fields
     * can be "mass assigned" by the framework. So if an attacker injects the
     * "ADMIN_ROLE" field in the request, it will not be processed by the application.
     *
     * Another technique to prevent mass assignment is to create a dedicated DTO Class
     * that only contains the fields that the user should be allowed to edit. Then
     * expose that dedicated Class in the request method.
     */
//    @InitBinder
//    public void initBinder(WebDataBinder binder, WebRequest request)
//    {
//        binder.setAllowedFields("id", "username", "email");
//    }


    // Home page of application
    @GetMapping("/")
    public String details() {

        return("UserSubmission");

    }  // end method


    // The page that asks to enter username
    @GetMapping("/getUserProfile")
    public String getUserProfilePage() {

        return("GetUserProfile");

    }  // end method


    // Used to submit user info to database
    // Mass Assignment vulnerability - User Class contains sensitive field "ADMIN_ROLE"
    @PostMapping("/submitData")
    public String detailsToDatabase(@Valid User users) {

        // Save the User Object (users) into the Database
        repo.save(users);

        return("UserSubmission");

    }  // end method


    // Return User by their username
    @PostMapping("/userProfile")
    public ModelAndView getUpdatedUserProfile(@RequestParam("username") String username) {

        ModelAndView mv = new ModelAndView("UserProfile");

        User user = repo.findByUsername(username);

        mv.addObject(user);

        return(mv);

    }  // end method


}  // end Class
