package com.example.repository;

import com.example.entity.CourseEntity;
import com.example.entity.StudentCourseMarkEntity;
import com.example.entity.StudentEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface StudentCourseMarkRepository extends CrudRepository<StudentCourseMarkEntity, Integer> {

    @Query("FROM StudentCourseMarkEntity where studentId = :student and createdDate = :created_date " +
            "order by mark")
    StudentCourseMarkEntity findByStudentIdAndCreatedDateOrderByMark(StudentEntity student, LocalDate created_date);

    List<StudentCourseMarkEntity> findAllByStudentIdAndCreatedDateBetween(StudentEntity studentId, LocalDate fromDate, LocalDate toDate);

    List<StudentCourseMarkEntity> findByStudentIdOrderByMarkDesc(StudentEntity student);

    List<StudentCourseMarkEntity> findByStudentIdAndCourseIdOrderByCourseIdDescMark(StudentEntity student, CourseEntity course);

    StudentCourseMarkEntity findFirstByStudentIdOrderByMarkAsc(StudentEntity student);

    StudentCourseMarkEntity findFirstByStudentIdOrderByMark(StudentEntity student);

    StudentCourseMarkEntity findFirstByStudentIdAndAndCourseIdOrderByMark(StudentEntity student, CourseEntity course);

    Integer countByCourseIdOrderByMark(CourseEntity course);
}
