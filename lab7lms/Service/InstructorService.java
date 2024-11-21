package com.example.lab7lms.Service;

import com.example.lab7lms.Model.Assignment;
import com.example.lab7lms.Model.Course;
import com.example.lab7lms.Model.Instructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InstructorService {
    private final ArrayList<Instructor> instructors=new ArrayList<>();

    public List<Instructor> getInstructors() {
        return instructors;
    }

    public void addInstructor(Instructor instructor) {
        instructors.add(instructor);
    }

    public boolean updateInstructor(String id,Instructor instructor) {
        for (Instructor i:instructors) {
            if (i.getID().equals(id)) {
                instructors.set(instructors.indexOf(i), instructor);
                return true;
            }
        }
        return false;
    }

    public boolean deleteInstructor(String id) {
        for (Instructor i:instructors) {
            if (i.getID().equals(id)) {
                instructors.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean createAssignment(String id,String courseID, Assignment assignment) {
        for (Instructor i:instructors) {
            if (i.getID().equals(id)) {
                for (Course c:i.getCourses()) {
                    if (c.getID().equals(courseID)) {
                        c.getAssignments().add(assignment);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean gradeAssignment(String id,String courseID, String assignmentID, int grade) {
        for (Instructor i:instructors) {
            if (i.getID().equals(id)) {
                for (Course c:i.getCourses()) {
                    if (c.getID().equals(courseID)) {
                        for (Assignment a:c.getAssignments()) {
                            if (a.getID().equals(assignmentID)) {
                                a.setGrade(grade);
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public List<Course> listCourses(String id) {
        for (Instructor i:instructors) {
            if (i.getID().equals(id)) {
                return i.getCourses();
            }
        }
        return null;
    }
}
