package com.example.lab7lms.Service;

import com.example.lab7lms.Model.Course;
import com.example.lab7lms.Model.Instructor;
import com.example.lab7lms.Model.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService {
    final ArrayList<Course> courses = new ArrayList<>();

    public List<Course> getCourses() {
        return courses;
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public boolean updateCourse(String id,Course course) {
        for (Course c : courses) {
            if (c.getID().equals(id)) {
                courses.set(courses.indexOf(c), course);
                return true;
            }
        }
        return false;
    }

    public boolean deleteCourse(String id) {
        for (Course c : courses) {
            if (c.getID().equals(id)) {
                courses.remove(c);
                return true;
            }
        }
        return false;
    }

    public boolean enrollStudent(String courseId, Student student) {
            for (Course c : courses) {
                if (c.getID().equals(courseId)) {
                    student.getCourses().add(c);
                    return true;
                }
            }
        return false;
    }

    public List<Student> listEnrolledStudent(String id) {
        for (Course c : courses) {
            if (c.getID().equals(id)) {
                return c.getStudents();
            }
        }
        return null;
    }

    public boolean assignInstructor(String id, Instructor instructor) {
        for (Course c : courses) {
            if (c.getID().equals(id)) {
                instructor.getCourses().add(c);
                return true;
            }
        }
        return false;
    }
}
