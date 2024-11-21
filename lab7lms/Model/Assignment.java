package com.example.lab7lms.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Assignment {

    @NotEmpty(message = "The ID should not be empty" )
    @Pattern(regexp = "^\\\\d{5}$",message = "The ID should be made of 5 numbers")
    private String ID;

    @NotEmpty(message = "The details should not be empty")
    @Size(min = 10, max = 100,message = "The details should be between 10 and 100 characters")
    private String details;

    @NotEmpty(message = "The submission content should not be empty")
    @Size(min = 30, max = 300,message = "The submission content should be between 30 and 300 characters")
    private String submissionContent;

    @Max(value = 10,message = "The grade value can't exceed 10")
    @Min(value = 1,message = "The grade should be more than 0")
    private int grade;
}
