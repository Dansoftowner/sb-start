package com.example.demo2.student;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class StudentService {
    public List<Student> getStudents() {
        return List.of(
                new Student(
                        1L,
                        "Laliel",
                        "laliel@c.com",
                        LocalDate.of(2000, 1, 12),
                        23
                )
        );
    }
}
