package com.example.controller;

import com.example.dto.StudentDto;
import com.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RestController
@RequestMapping(value = "/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping(value = "/create")
    public ResponseEntity<?> create(@RequestBody StudentDto studentDTO) {
        studentDTO.setCreated_date(LocalDateTime.now());
        StudentDto response = studentService.create(studentDTO);
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/get/All")
    public ResponseEntity<List<StudentDto>> getAll(){
        List<StudentDto> list = studentService.getAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping(value = "/get/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
        StudentDto dto = studentService.getById(id);
        return ResponseEntity.ok(dto);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody StudentDto studentDTO) {
        return ResponseEntity.ok(studentService.update(id, studentDTO));
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(studentService.delete(id));
    }

    @GetMapping(value = "/getByName{name}")
    public ResponseEntity<?> getByName(@PathVariable("name") String name){
        StudentDto dto = studentService.getByName(name);
        return ResponseEntity.ok(dto);
    }
}
