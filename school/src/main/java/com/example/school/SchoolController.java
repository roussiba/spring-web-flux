package com.example.school;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/school")
public class SchoolController {

    private final SchoolService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody School school){
        service.saveSchool(school);
    }

    @GetMapping
    public ResponseEntity<List<School>> findAllSchool(){
        return ResponseEntity.ok(service.findAllSchool());
    }

    @GetMapping("/with-students/{school-id}")
    public ResponseEntity<FullSchoolResponse> findAllSchool(@PathVariable("school-id") Integer schoolId){
        return ResponseEntity.ok(service.findSchoolsWithStudents(schoolId));
    }
}
