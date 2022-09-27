package com.cydeo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor// for an empty object in the form while its blanks
public class Employee {

    private String firstName;
    private String lastName;
    //ThymeLeaf accepts yyyy-MM-dd this format when saves data, but LocalDate accepts mm-dd-yyyy
    //That is why formatting required
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;
    private String email;
    private String password;
    private String address;
    private String address2;
    private String city;
    private String state;
    private String zipCode;

}
