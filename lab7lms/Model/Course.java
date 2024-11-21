package com.example.lab7lms.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class Course {


    @NotEmpty(message = "The ID should not be empty" )
    @Pattern(regexp = "^\\\\d{5}$",message = "The ID should be made of 5 numbers")
    private String ID;

    @NotEmpty(message = "The name should not be empty")
    private String name;

    @NotEmpty(message = "The description should not be empty")
    @Size(min = 10, max = 100,message = "The description should be between 10 and 100 characters")
    private String description;

    @Size(max = 0,message = "The students list should be empty")
    private ArrayList<Student> students;

    @Size(max = 0,message = "The assignments list should be empty")
    private ArrayList<Assignment> assignments;
}
