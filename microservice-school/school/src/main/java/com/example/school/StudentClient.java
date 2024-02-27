package com.example.school;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "students-service", url = "${spring.application.students-url}" )
public interface StudentClient {

    @GetMapping("/school/{school-id}")
    List<Student> findAllStudentsBySchool(@PathVariable("school-id") Integer schoolId);
}