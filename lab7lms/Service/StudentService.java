package com.example.lab7lms.Service;

import com.example.lab7lms.Model.Assignment;
import com.example.lab7lms.Model.Course;
import com.example.lab7lms.Model.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    final ArrayList<Student> students = new ArrayList<>();

    public List<Student> getStudents() {
        return students;
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public boolean updateStudent(String id,Student student) {
        for (Student s : students) {
            if (s.getID().equals(id)) {
                students.set(students.indexOf(student), student);
                return true;
            }
        }
        return false;
    }

    public boolean deleteStudent(String id) {
        for (Student s : students) {
            if (s.getID().equals(id)) {
                students.remove(s);
                return true;
            }
        }
        return false;
    }

    public List<Course> viewCourses(String id) {
        for (Student s : students) {
            if (s.getID().equals(id)) {
                return s.getCourses();
            }
        }
        return null;
    }

    public boolean submitAssignment(String id,String courseId,String assignmentID, String content) {
        for (Student s : students) {
            if (s.getID().equals(id)) {
                for (Course c : s.getCourses()) {
                    if (c.getID().equals(courseId)) {
                        for (Assignment a : c.getAssignments()) {
                            if (a.getID().equals(assignmentID)) {
                                a.setSubmissionContent(content);
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public String checkProgress(String id, String courseId) {
        for (Student s : students) {
            if (s.getID().equals(id)) {
                for (Course c : s.getCourses()) {
                    if (c.getID().equals(courseId)) {
                        return "In progress";
                    }
                }
                return "Not taking the course";
            }
        }
        return null;
    }
}
