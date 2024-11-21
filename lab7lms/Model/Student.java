package com.example.lab7lms.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDate;
import java.util.ArrayList;

@Data
@AllArgsConstructor
public class Student {

    @NotEmpty(message = "The ID should not be empty" )
    @Pattern(regexp = "^\\\\d{5}$",message = "The ID should be made of 5 numbers")
    private String ID;

    @NotEmpty(message = "The name should not be empty")
    private String name;

    @NotEmpty(message ="The email should not be empty" )
    @Email(message = "Wrong email format")
    private String email;

    @NotNull(message = "The start date should not be empty")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate finishDate;

    @AssertFalse
    private boolean isGraduated;

    @Size(max = 0,message = "The courses list should be empty")
    private ArrayList<Course> courses;

}
