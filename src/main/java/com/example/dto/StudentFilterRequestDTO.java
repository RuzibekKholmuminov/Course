package com.example.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentFilterRequestDTO {
    private String name;
    private Integer level;
    private String gender;
}
