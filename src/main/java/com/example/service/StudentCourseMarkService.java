package com.example.service;

import com.example.dto.CourseDto;
import com.example.dto.StudentCourseMarkDto;
import com.example.entity.CourseEntity;
import com.example.entity.StudentCourseMarkEntity;
import com.example.entity.StudentEntity;
import com.example.exp.AppBadRequestException;
import com.example.repository.StudentCourseMarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentCourseMarkService {
    @Autowired
    private StudentCourseMarkRepository studentCourseMarkRepository;

    public StudentCourseMarkDto create(StudentCourseMarkDto dto) {
        CourseEntity courseEntity = new CourseEntity();
        courseEntity.setId(dto.getCourse_id());
        StudentCourseMarkEntity entity = new StudentCourseMarkEntity();
        entity.setCourse_id(courseEntity);
        if (dto.getCourse_id() == null) {
            throw new AppBadRequestException("Course Id qani?");
        }
        StudentEntity student = new StudentEntity();
        student.setId(dto.getStudent_id());
        entity.setStudent_id(student);
        if (dto.getStudent_id() == null) {
            throw new AppBadRequestException("Student Id qani?");
        }

        entity.setMark(dto.getMark());
        if (dto.getMark() == null || dto.getMark().isBlank()) {
            throw new AppBadRequestException("Mark qani?");
        }

        entity.setCreated_date(dto.getCreatedDate());
        studentCourseMarkRepository.save(entity);
        dto.setId(entity.getId());
        return dto;
    }

    public Object update(Integer id, StudentCourseMarkDto dto) {
        StudentCourseMarkEntity entity = get(id);
        StudentEntity student = new StudentEntity();
        student.setId(dto.getStudent_id());
        entity.setStudent_id(student);
        CourseEntity course = new CourseEntity();
        course.setId(dto.getCourse_id());
        entity.setCourse_id(course);
        entity.setMark(dto.getMark());
        entity.setCreated_date(dto.getCreatedDate());
        studentCourseMarkRepository.save(entity);
        return true;
    }

    public StudentCourseMarkEntity get(Integer id) {
        Optional<StudentCourseMarkEntity> entity = studentCourseMarkRepository.findById(id);
        if (entity.isEmpty()) {
            throw new AppBadRequestException("Student not found: " + id);
        }
        return entity.get();
    }

    public StudentCourseMarkDto getById(Integer id) {
        StudentCourseMarkEntity entity = get(id);
        StudentCourseMarkDto dto = new StudentCourseMarkDto();
        dto.setId(entity.getId());
        dto.setCourse_id(entity.getCourse_id().getId());
        dto.setStudent_id(entity.getStudent_id().getId());
        dto.setMark(entity.getMark());
        dto.setCreatedDate(entity.getCreated_date());

        return dto;

    }

    public List<StudentCourseMarkDto> getAll() {
        Iterable<StudentCourseMarkEntity> iterable = studentCourseMarkRepository.findAll();
        List<StudentCourseMarkDto> studentDTOLinkedList = new LinkedList<>();
        iterable.forEach(entity -> {
            StudentCourseMarkDto dto = new StudentCourseMarkDto();
            dto.setId(entity.getId());
            dto.setCourse_id(entity.getCourse_id().getId());
            dto.setStudent_id(entity.getStudent_id().getId());
            dto.setMark(entity.getMark());
            dto.setCreatedDate(entity.getCreated_date());
            studentDTOLinkedList.add(dto);
        });
        return studentDTOLinkedList;
    }

    public boolean delete(Integer id) {
        StudentCourseMarkEntity entity = get(id);
        studentCourseMarkRepository.delete(entity);
        return true;
    }
}
