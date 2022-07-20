package com.example.demo2.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

//@org.springframework.stereotype.Component
@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        studentRepository.findStudentByEmail(student.getEmail()).ifPresentOrElse(st -> {
            throw new IllegalStateException("email taken");
        }, () -> {
            studentRepository.save(student);
        });
    }

    public void deleteStudent(Long id) {
        if (!studentRepository.existsById(id))
            throw new IllegalStateException("student with id '%s' does not exist".formatted(id));
        studentRepository.deleteById(id);
    }

    @Transactional
    public void updateStudent(Long id, String name, String email) {
        studentRepository.findById(id).ifPresentOrElse(it -> {
            if (Objects.nonNull(name) && !Objects.equals(it.getName(), name))
                it.setName(name);
            if (Objects.nonNull(email) && !Objects.equals(it.getEmail(), email)) {
                studentRepository.findStudentByEmail(email).ifPresentOrElse(st -> {
                    throw new IllegalStateException("Email taken");
                }, () -> it.setEmail(email));
            }
        }, () -> {
            throw new IllegalStateException("student with id '%s' does not exist!".formatted(id));
        });
    }
}
