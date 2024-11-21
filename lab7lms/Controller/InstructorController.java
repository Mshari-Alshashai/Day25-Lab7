package com.example.lab7lms.Controller;

import com.example.lab7lms.ApiResponse.ApiResponse;
import com.example.lab7lms.Model.Assignment;
import com.example.lab7lms.Model.Instructor;
import com.example.lab7lms.Service.InstructorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/instructor")
@RequiredArgsConstructor
public class InstructorController {
    private final InstructorService instructorService;

    @GetMapping("/get")
    public ResponseEntity getInstructors(){
        return ResponseEntity.status(200).body(instructorService.getInstructors());
    }

    @PostMapping("/add")
    public ResponseEntity addInstructor(@RequestBody @Valid Instructor instructor, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        instructorService.addInstructor(instructor);
        return ResponseEntity.status(200).body(new ApiResponse("Instructor added successfully"));

    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateInstructor(@PathVariable String id, @RequestBody @Valid Instructor instructor, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        if (instructorService.updateInstructor(id, instructor)) return ResponseEntity.status(200).body(new ApiResponse("Instructor updated successfully"));
        return ResponseEntity.status(400).body(new ApiResponse("Instructor not found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteInstructor(@PathVariable String id){
        if (instructorService.deleteInstructor(id)) {
            return ResponseEntity.status(200).body(new ApiResponse("Instructor deleted successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Instructor not found"));
    }

    @PostMapping("/create-assignment/{id}/{courseID}")
    public ResponseEntity createAssignment(@PathVariable String id,@PathVariable String courseID,@RequestBody @Valid Assignment assignment, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        if (instructorService.createAssignment(id, courseID, assignment)) return ResponseEntity.status(200).body(new ApiResponse("Assignment created successfully"));
        return ResponseEntity.status(400).body(new ApiResponse("Failed to create the assignment"));
    }

    @PutMapping("/grade-assignment/{id}/{courseID}/{assignmentID}/{grade}")
    public ResponseEntity gradeAssignment(@PathVariable String id,@PathVariable String courseID,@PathVariable String assignmentID,@PathVariable int grade){
        if (instructorService.gradeAssignment(id, courseID, assignmentID, grade)) {
            return ResponseEntity.status(200).body(new ApiResponse("Assignment graded successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Failed to grade the assignment"));
    }

    @GetMapping("/list-courses/{id}")
    public ResponseEntity listCourses(@PathVariable String id){
        if (instructorService.listCourses(id)==null) {
            return ResponseEntity.status(400).body(new ApiResponse("Failed to list the courses"));
        }
        return ResponseEntity.status(200).body(instructorService.listCourses(id));
    }
}
