package com.example.repository;

import com.example.entity.CourseEntity;
import com.example.entity.StudentCourseMarkEntity;
import com.example.entity.StudentEntity;
import com.example.mapper.CourseInfoMapper;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

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

    @Query(value = "SELECT course_id from  student_course_mark where student_id = :studentId order by created_date desc limit 1 ", nativeQuery = true)
    Integer findLastCourseMarker(@Param("studentId") Integer studentId);

    @Query(value = "SELECT c.id, c.name " +
            " from  student_course_mark as scm " +
            " inner join course_t as c on c.id = scm.course_id " +
            " where scm.student_id = :studentId  " +
            "order by scm.created_date desc limit 1 ", nativeQuery = true)
    List<Object[]> findLastCourseMarkerAsNative(@Param("studentId") Integer studentId);

    @Query(value = "SELECT scm.student_id as sId, scm.mark as mark, " +
            "  c.id as cId, c.name as cName " +
            " from  student_course_entity as scm " +
            " inner join course as c on c.id = scm.course_id " +
            " where scm.student_id = :studentId  " +
            "order by scm.created_date desc limit 1 ", nativeQuery = true)
    CourseInfoMapper findLastCourseMarkerAsNativeMapping(@Param("studentId") Integer studentId);

}
