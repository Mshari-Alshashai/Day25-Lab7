package com.example.lab7lms.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class Instructor {

    @NotEmpty(message = "The ID should not be empty" )
    @Pattern(regexp = "^\\\\d{5}$",message = "The ID should be made of 5 numbers")
    private String ID;

    @NotEmpty(message = "The name should not be empty")
    private String name;

    @Size(max = 0,message = "The courses list should be empty")
    private ArrayList<Course> courses;

}
