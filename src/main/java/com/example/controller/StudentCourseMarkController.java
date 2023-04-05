package com.example.controller;

import com.example.dto.CourseDto;
import com.example.dto.StudentCourseMarkDto;
import com.example.dto.StudentDto;
import com.example.service.StudentCourseMarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RestController
@RequestMapping(value = "student_course_mark")
public class StudentCourseMarkController {
    @Autowired
    private StudentCourseMarkService studentCourseMarkService;

    @PostMapping(value = "/create")
    public ResponseEntity<?> create(@RequestBody StudentCourseMarkDto studentDTO) {
        StudentCourseMarkDto response = studentCourseMarkService.create(studentDTO);
        return ResponseEntity.ok(response);
    }
    @PutMapping(value = "/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody StudentCourseMarkDto courseDto) {
        return ResponseEntity.ok(studentCourseMarkService.update(id, courseDto));
    }

    @GetMapping(value = "/get/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
        StudentCourseMarkDto dto = studentCourseMarkService.getById(id);
        return ResponseEntity.ok(dto);
    }
    @GetMapping(value = "/get/All")
    public ResponseEntity<List<StudentCourseMarkDto>> getAll(){
        List<StudentCourseMarkDto> list = studentCourseMarkService.getAll();
        return ResponseEntity.ok(list);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(studentCourseMarkService.delete(id));
    }
}
