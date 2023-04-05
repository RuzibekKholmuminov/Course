package com.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "Student_course_entity")
public class StudentCourseMarkEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private StudentEntity student_id;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private CourseEntity course_id;
}
