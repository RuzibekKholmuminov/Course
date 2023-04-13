package com.example.controller;

import com.example.dto.StudentCourseMarkDto;
import com.example.dto.StudentDto;
import com.example.dto.StudentFilterRequestDTO;
import com.example.dto.StudentMarkFilter;
import com.example.entity.CourseEntity;
import com.example.entity.StudentEntity;
import com.example.service.StudentCourseMarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

    @GetMapping(value = "/getByDate")
    public ResponseEntity<?> getByDate(@RequestParam("studentId") Integer id, @RequestParam("created_date") LocalDate created_date){
        StudentEntity student = new StudentEntity();
        student.setId(id);
        StudentCourseMarkDto dto = studentCourseMarkService.getByDate(student, created_date);
        return ResponseEntity.ok(dto);
    }

    @GetMapping(value = "/getBetweenDate")
    private ResponseEntity<List<StudentCourseMarkDto>> getBetweenDate(@RequestParam("studentId") Integer sId,
                                             @RequestParam("fromDate") LocalDate fromDate,
                                             @RequestParam("toDate") LocalDate toDate){
        StudentEntity student = new StudentEntity();
        student.setId(sId);
        List<StudentCourseMarkDto> dto = studentCourseMarkService.
                getStudentCourseMarkListBetweenDates(student, fromDate, toDate);
        return ResponseEntity.ok(dto);
    }

    @GetMapping(value = "/getAllMark")
    private ResponseEntity<List<StudentCourseMarkDto>> getAllMark(@RequestParam("studentId") Integer sId){
        StudentEntity student = new StudentEntity();
        student.setId(sId);
        List<StudentCourseMarkDto> dto = studentCourseMarkService.getAllStudentMark(student);
        return ResponseEntity.ok(dto);
    }

    @GetMapping(value = "/getDateMark")
    private ResponseEntity<List<StudentCourseMarkDto>> getAllMark(@RequestParam("studentId") Integer sId,
                                                                  @RequestParam("courseId") Integer cId){
        StudentEntity student = new StudentEntity();
        student.setId(sId);
        CourseEntity course = new CourseEntity();
        course.setId(cId);
        List<StudentCourseMarkDto> dto = studentCourseMarkService.getDateMark(student,course);
        return ResponseEntity.ok(dto);
    }
    @GetMapping(value = "/getTopMark/{id}")
    public ResponseEntity<?> getFirstMark(@PathVariable("id") Integer id) {
        StudentEntity student = new StudentEntity();
        student.setId(id);
        StudentCourseMarkDto dto = studentCourseMarkService.getFirstMark(student);
        return ResponseEntity.ok(dto);
    }

    @GetMapping(value = "/getLastMark/{id}")
    public ResponseEntity<?> getLastMark(@PathVariable("id") Integer id) {
        StudentEntity student = new StudentEntity();
        student.setId(id);
        StudentCourseMarkDto dto = studentCourseMarkService.getLastMark(student);
        return ResponseEntity.ok(dto);
    }

    @GetMapping(value = "/getStudentCourseFirstMark")
    public ResponseEntity<?> getLastMark(@RequestParam("studentId") Integer sId,
                                         @RequestParam("courseId") Integer cId) {
        StudentEntity student = new StudentEntity();
        student.setId(sId);
        CourseEntity course = new CourseEntity();
        course.setId(cId);
        StudentCourseMarkDto dto = studentCourseMarkService.getStudentCurseFirstMark(student, course);
        return ResponseEntity.ok(dto);
    }

    @GetMapping(value = "/countCourseMark")
    public ResponseEntity<?> countCourseMark(@RequestParam("courseId") Integer cId) {
        CourseEntity course = new CourseEntity();
        course.setId(cId);
        Integer count = studentCourseMarkService.countCourseMark( course);
        return ResponseEntity.ok(count);
    }

    @GetMapping(value = "/test/{id}")
    public ResponseEntity<?> test(@PathVariable("id") Integer id){
        studentCourseMarkService.test();
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/test2/{id}")
    public ResponseEntity<?> test2(@PathVariable("id") Integer id){
        studentCourseMarkService.test2();
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/paging")
    public ResponseEntity<?> test2(@RequestParam(value = "page", defaultValue = "1") int page,
                                   @RequestParam(value = "size", defaultValue = "30") int size) {
        Page<StudentCourseMarkDto> studentDtoPage = studentCourseMarkService.pagination(1,2);
        return ResponseEntity.ok(studentDtoPage);
    }

    @PostMapping(value = "/paging-student-id")
    public ResponseEntity<Page<StudentCourseMarkDto>> pagingWithStudentId(@RequestParam(value = "page", defaultValue = "1") int page,
                                                           @RequestParam(value = "size", defaultValue = "30") int size,
                                                           @RequestBody StudentMarkFilter filter) {
        Page<StudentCourseMarkDto> response = studentCourseMarkService.paginationWithStudentId(filter.getStudentId(), page, size);
        return ResponseEntity.ok(response);
    }
    @PostMapping(value = "/paging-course-id")
    public ResponseEntity<Page<StudentCourseMarkDto>> pagingWithCourseId(@RequestParam(value = "page", defaultValue = "1") int page,
                                                                     @RequestParam(value = "size", defaultValue = "30") int size,
                                                                     @RequestBody StudentMarkFilter filter) {
        Page<StudentCourseMarkDto> response = studentCourseMarkService.paginationWithCourseId(filter.getCourseId(), page, size);
        return ResponseEntity.ok(response);
    }

}
