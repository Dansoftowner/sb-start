package com.example.demo2.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
        return args -> {
            var st = new Student(
                    "Laliel",
                    "laliel@c.com",
                    LocalDate.of(2000, 1, 12)
            );
            var st2 = new Student(
                    "JAJIel",
                    "dsfdsfl@c.com",
                    LocalDate.of(1999, 1, 12)
            );
            studentRepository.saveAll(List.of(st, st2));
        };
    }
}
