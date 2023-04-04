package com.example.controller;

import com.example.dto.StudentDto;
import com.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
        studentDTO.setCreated_date(LocalDate.now());
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

    @GetMapping(value = "/getByName/{name}")
    public ResponseEntity<?> getByName(@PathVariable("name") String name){
        StudentDto dto = studentService.getByName(name);
        return ResponseEntity.ok(dto);
    }
    @GetMapping(value = "/getBySurname/{surname}")
    public ResponseEntity<?> getBySurname(@PathVariable("surname") String surname){
        StudentDto dto = studentService.getBySurname(surname);
        return ResponseEntity.ok(dto);
    }

    @GetMapping(value = "/getByLevel/{level}")
    public ResponseEntity<?> getByLevel(@PathVariable("level") Integer level){
        StudentDto dto = studentService.getByLevel(level);
        return ResponseEntity.ok(dto);
    }
    @GetMapping(value = "/getByAge/{age}")
    public ResponseEntity<?> getByAge(@PathVariable("age") Integer age){
        StudentDto dto = studentService.getByAge(age);
        return ResponseEntity.ok(dto);
    }
    @GetMapping(value = "/getByGender/{gender}")
    public ResponseEntity<?> getByGender(@PathVariable("gender") String gender){
        StudentDto dto = studentService.getByGender(gender);
        return ResponseEntity.ok(dto);
    }

}
