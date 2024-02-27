package com.example.school;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SchoolService {

    private final SchoolRepository repository;
    private final StudentClient studentClient;

    public void saveSchool(School student){
        repository.save(student);
    }

    public List<School> findAllSchool(){
        return repository.findAll();
    }

    public FullSchoolResponse findSchoolsWithStudents(Integer schoolId) {
        var school = repository.findById(schoolId)
                .orElse(
                        School.builder()
                                .name("NOT_FOUND")
                                .email("NOT_FOUND")
                                .build()
                );
        var students = studentClient.findAllStudentsBySchool(schoolId);

        return FullSchoolResponse.builder()
                .students(students)
                .name(school.getName())
                .email(school.getEmail())
                .build();
    }
}
