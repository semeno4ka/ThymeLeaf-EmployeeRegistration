package com.cydeo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor// for an empty object in the form while its blanks
public class Employee {
/*@NotNull field cannot be null
@NotBlank  cannot be left blank -> "  " -> String str="  " (Covers All Null and Empty)
@NotEmpty  should contain data  -> ""   -> String str=""   (Covers Null)*/
    @NotBlank
    @Size(max=12, min=2)
    private String firstName;
    private String lastName;
    //ThymeLeaf accepts yyyy-MM-dd this format when saves data, but LocalDate accepts mm-dd-yyyy
    //That is why formatting required

  //  @NotNull // for LocalDate is Better NotNull
  // @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;

 //   @NotBlank
 //   @Email
    private String email;

 //   @NotBlank
 //   @Pattern(regexp = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{4,}")
    private String password;
    private String address;
    private String address2;
    private String city;
    private String state;
    private String zipCode;

}
