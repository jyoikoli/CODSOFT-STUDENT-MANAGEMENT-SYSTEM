package STUDENT MANAGEMENT SYSTEM;

import java.util.ArrayList;
import java.util.List;

public class StudentManagementSystem {
    private List<Student> studentList;

    public StudentManagementSystem() {
        studentList = new ArrayList<>();
    }

    public void addStudent(Student student) {
        studentList.add(student);
    }

    public void removeStudent(Student student) {
        studentList.remove(student);
    }

    public Student searchStudent(int rollNumber) {
        for (Student student : studentList) {
            if (student.getRollNumber() == rollNumber) {
                return student;
            }
        }
        return null;
    }

    public List<Student> getAllStudents() {
        return studentList;
    }
}

