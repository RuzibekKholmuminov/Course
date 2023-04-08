package com.example.dto;

import com.example.entity.CourseEntity;
import com.example.entity.StudentEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class StudentCourseMarkDto {
    private Integer id;
    private Integer studentId;
    private Integer courseId;
    private String mark;
    private LocalDate createdDate;
}
