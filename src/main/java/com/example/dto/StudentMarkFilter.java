package com.example.dto;

import com.example.entity.CourseEntity;
import com.example.entity.StudentEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentMarkFilter {
    private Integer id;
    private Integer studentId;
    private Integer courseId;
}
