package com.example.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.stereotype.Component;

@Entity
@Component
@Table(name = "users")
public class User {

    // Class has 4 fields (id, username, email, ADMIN_ROLE)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Size(max = 10)
    @Pattern(regexp = "[\\w\\d\\s]+")
    private String username;

    private String email;

    private Boolean ADMIN_ROLE;


    // Set and get methods
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getADMIN_ROLE() {
        return ADMIN_ROLE;
    }

    public void setADMIN_ROLE(Boolean ADMIN_ROLE) {
        this.ADMIN_ROLE = ADMIN_ROLE;
    }

    @Override
    public String toString() {

        if(this.ADMIN_ROLE == null) {
            this.ADMIN_ROLE = false;
        }

        return "User " + "[id=" + id + ", " + "username=" + username + ", " + "email=" + email + ", " + "ADMIN_ROLE=" + ADMIN_ROLE + "]";
    }


}  // end Class
