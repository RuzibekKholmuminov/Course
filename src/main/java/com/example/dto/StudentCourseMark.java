package com.example.dto;

import com.example.entity.CourseEntity;
import com.example.entity.StudentEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class StudentCourseMark {
    private Integer id;
    private StudentEntity student_id;
    private CourseEntity course_id;
    private String mark;
    private LocalDate createdDate;
}
