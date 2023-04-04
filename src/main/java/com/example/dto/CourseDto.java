package com.example.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CourseDto {
    private Integer id;
    private String name;
    private Integer price;
    private String duration;
    private LocalDate created_date;
}
