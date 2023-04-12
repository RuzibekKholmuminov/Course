package com.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@Entity
@Table(name = "student_course_entity")
public class StudentCourseMarkEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private StudentEntity studentId;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private CourseEntity courseId;

    @Column(name = "mark")
    private String mark;

    @Column(name = "created_date")
    private LocalDate createdDate;
}
