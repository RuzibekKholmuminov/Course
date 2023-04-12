package com.example.controller;

import com.example.dto.CourseDto;
import com.example.dto.CourseFilterRequestDTO;
import com.example.dto.StudentDto;
import com.example.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RestController
@RequestMapping(value = "/course")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @PostMapping(value = "/create")
    public ResponseEntity<?> create(@RequestBody CourseDto courseDto) {
        CourseDto response = courseService.create(courseDto);
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/get/All")
    public ResponseEntity<List<CourseDto>> getAll(){
        List<CourseDto> list = courseService.getAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping(value = "/get/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
        CourseDto dto = courseService.getById(id);
        return ResponseEntity.ok(dto);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody CourseDto courseDto) {
        return ResponseEntity.ok(courseService.update(id, courseDto));
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(courseService.delete(id));
    }
    @GetMapping(value = "/getByName/{name}")
    public ResponseEntity<?> getByName(@PathVariable("name") String name){
        CourseDto dto = courseService.getByName(name);
        return ResponseEntity.ok(dto);
    }
    @GetMapping(value = "/getByPrice/{price}")
    public ResponseEntity<?> getByPrice(@PathVariable("price") Integer name){
        CourseDto dto = courseService.getByPrice(name);
        return ResponseEntity.ok(dto);
    }
    @GetMapping(value = "/getByDuration/{duration}")
    public ResponseEntity<?> getByDuration(@PathVariable("duration") String duration){
        CourseDto dto = courseService.getByDuration(duration);
        return ResponseEntity.ok(dto);
    }

    @GetMapping(value = "/paging")
    public ResponseEntity<?> paging(@RequestParam(value = "page", defaultValue = "1") int page,
                                   @RequestParam(value = "size", defaultValue = "30") int size) {

        Page<CourseDto> studentDtoPage = courseService.pagination(1,2);
        return ResponseEntity.ok(studentDtoPage);
    }

    @GetMapping(value = "/pagingByPrice")
    public ResponseEntity<?> pagingByPrice(@RequestParam(value = "page", defaultValue = "1") int page,
                                           @RequestParam(value = "size", defaultValue = "30") int size,
                                           @RequestBody CourseFilterRequestDTO filter) {
        Page<CourseDto> studentDtoPage = courseService.paginationByPrice(filter.getPrice(),1,2);
        return ResponseEntity.ok(studentDtoPage);
    }
}
