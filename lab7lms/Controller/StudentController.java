package com.example.lab7lms.Controller;

import com.example.lab7lms.ApiResponse.ApiResponse;
import com.example.lab7lms.Model.Student;
import com.example.lab7lms.Service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/student")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @GetMapping("/get")
    public ResponseEntity getStudents() {
        return ResponseEntity.status(200).body(studentService.getStudents());
    }

    @PostMapping("/add")
    public ResponseEntity addStudent(@RequestBody @Valid Student student, Errors errors){
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        studentService.addStudent(student);
        return ResponseEntity.status(200).body(new ApiResponse("Student added successfully"));

    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateStudent(@PathVariable String id, @RequestBody @Valid Student student, Errors errors){
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        if (studentService.updateStudent(id, student)) return ResponseEntity.status(200).body(new ApiResponse("Student updated"));
        return ResponseEntity.status(400).body(new ApiResponse("Student not found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteStudent(@PathVariable String id) {
        if (studentService.deleteStudent(id)) return ResponseEntity.status(200).body(new ApiResponse("Student deleted"));
        return ResponseEntity.status(400).body(new ApiResponse("Student not found"));
    }

    @GetMapping("/view-courses/{id}")
    public ResponseEntity viewCourses(@PathVariable String id){
        if (studentService.viewCourses(id)==null) return ResponseEntity.status(400).body(new ApiResponse("Student not found"));
        return ResponseEntity.status(200).body(studentService.viewCourses(id));
    }

    @PutMapping("/submit-assignment/{id}/{courseId}/{assignmentID}")
    public ResponseEntity submitAssignment(@PathVariable String id, @PathVariable String courseId, @PathVariable String assignmentID, @RequestBody String content){
        if (studentService.submitAssignment(id, courseId, assignmentID, content)) return ResponseEntity.status(200).body(new ApiResponse("Student submitted"));
        return ResponseEntity.status(400).body(new ApiResponse("Student not submitted"));
    }

    @GetMapping("/check-progress/{id}/{courseId}")
    public ResponseEntity checkProgress(@PathVariable String id, @PathVariable String courseId){
        if (studentService.checkProgress(id,courseId)==null) return ResponseEntity.status(400).body(new ApiResponse("Student not found"));
        return ResponseEntity.status(200).body(studentService.checkProgress(id,courseId));
    }
}
