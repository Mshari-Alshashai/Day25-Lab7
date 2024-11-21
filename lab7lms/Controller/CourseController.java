package com.example.lab7lms.Controller;

import com.example.lab7lms.ApiResponse.ApiResponse;
import com.example.lab7lms.Model.Course;
import com.example.lab7lms.Model.Instructor;
import com.example.lab7lms.Model.Student;
import com.example.lab7lms.Service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/course")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @GetMapping("/get")
    public ResponseEntity getCourses(){
        return ResponseEntity.status(200).body(courseService.getCourses());
    }

    @PostMapping("/add")
    public ResponseEntity addCourse(@RequestBody @Valid Course course, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.badRequest().body(errors.getFieldError().getDefaultMessage());
        }
        courseService.addCourse(course);
        return ResponseEntity.status(200).body(new ApiResponse("Course added successfully"));

    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateCourse(@PathVariable String id, @RequestBody @Valid Course course, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.badRequest().body(errors.getFieldError().getDefaultMessage());
        }
        if (courseService.updateCourse(id, course)) {
            return ResponseEntity.status(200).body(new ApiResponse("Course updated successfully"));
        }
        return ResponseEntity.badRequest().body(new ApiResponse("Course not found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCourse(@PathVariable String id){
        if (courseService.deleteCourse(id)) {
            return ResponseEntity.status(200).body(new ApiResponse("Course deleted successfully"));
        }
        return ResponseEntity.badRequest().body(new ApiResponse("Course not found"));
    }

    @PutMapping("/enroll-student/{courseId}")
    public ResponseEntity enrollStudent(@PathVariable String courseId, @RequestBody @Valid Student student, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.badRequest().body(errors.getFieldError().getDefaultMessage());
        }

        if (courseService.enrollStudent(courseId,student)) return ResponseEntity.status(200).body(new ApiResponse("Student enrolled successfully"));
        return ResponseEntity.badRequest().body(new ApiResponse("Course not found"));
    }

    @GetMapping("/list-enrolled-students")
    public ResponseEntity listEnrolledStudent(String id){
        if (courseService.listEnrolledStudent(id)==null) return ResponseEntity.badRequest().body(new ApiResponse("Course not found"));
        return ResponseEntity.status(200).body(courseService.listEnrolledStudent(id));
    }

    @PutMapping("/assign/{id}")
    public ResponseEntity assignInstructor(@PathVariable String id,@RequestBody @Valid Instructor instructor, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.badRequest().body(errors.getFieldError().getDefaultMessage());
        }
        if (courseService.assignInstructor(id, instructor)) return ResponseEntity.status(200).body(new ApiResponse("Instructor assigned successfully"));
        return ResponseEntity.badRequest().body(new ApiResponse("Course not found"));
    }
}
