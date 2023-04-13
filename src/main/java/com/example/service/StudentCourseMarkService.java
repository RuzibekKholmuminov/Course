package com.example.service;

import com.example.dto.CorseDTO;
import com.example.dto.StudentCourseMarkDto;
import com.example.dto.StudentDto;
import com.example.entity.CourseEntity;
import com.example.entity.StudentCourseMarkEntity;
import com.example.entity.StudentEntity;
import com.example.exp.AppBadRequestException;
import com.example.mapper.CourseInfoMapper;
import com.example.repository.StudentCourseMarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentCourseMarkService {
    @Autowired
    private StudentCourseMarkRepository studentCourseMarkRepository;

    public StudentCourseMarkDto create(StudentCourseMarkDto dto) {
        CourseEntity courseEntity = new CourseEntity();
        courseEntity.setId(dto.getCourseId());
        StudentCourseMarkEntity entity = new StudentCourseMarkEntity();
        entity.setCourseId(courseEntity);
        if (dto.getCourseId() == null) {
            throw new AppBadRequestException("Course Id qani?");
        }
        StudentEntity student = new StudentEntity();
        student.setId(dto.getStudentId());
        entity.setStudentId(student);
        if (dto.getStudentId() == null) {
            throw new AppBadRequestException("Student Id qani?");
        }

        entity.setMark(dto.getMark());
        if (dto.getMark() == null || dto.getMark().isBlank()) {
            throw new AppBadRequestException("Mark qani?");
        }

        entity.setCreatedDate(LocalDate.parse("2022-01-03"));
        studentCourseMarkRepository.save(entity);
        dto.setId(entity.getId());
        return dto;
    }

    public Object update(Integer id, StudentCourseMarkDto dto) {
        StudentCourseMarkEntity entity = get(id);
        StudentEntity student = new StudentEntity();
        student.setId(dto.getStudentId());
        entity.setStudentId(student);
        CourseEntity course = new CourseEntity();
        course.setId(dto.getStudentId());
        entity.setCourseId(course);
        entity.setMark(dto.getMark());
        entity.setCreatedDate(dto.getCreatedDate());
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
        dto.setCourseId(entity.getCourseId().getId());
        dto.setStudentId(entity.getStudentId().getId());
        dto.setMark(entity.getMark());
        dto.setCreatedDate(entity.getCreatedDate());

        return dto;

    }

    public List<StudentCourseMarkDto> getAll() {
        Iterable<StudentCourseMarkEntity> iterable = studentCourseMarkRepository.findAll();
        List<StudentCourseMarkDto> studentDTOLinkedList = new LinkedList<>();
        iterable.forEach(entity -> {
            StudentCourseMarkDto dto = new StudentCourseMarkDto();
            dto.setId(entity.getId());
            dto.setCourseId(entity.getCourseId().getId());
            dto.setStudentId(entity.getStudentId().getId());
            dto.setMark(entity.getMark());
            dto.setCreatedDate(entity.getCreatedDate());
            studentDTOLinkedList.add(dto);
        });
        return studentDTOLinkedList;
    }

    public boolean delete(Integer id) {
        StudentCourseMarkEntity entity = get(id);
        studentCourseMarkRepository.delete(entity);
        return true;
    }

    public StudentCourseMarkDto getByDate(StudentEntity student_id, LocalDate date) {
        StudentCourseMarkEntity entity = studentCourseMarkRepository.
                findByStudentIdAndCreatedDateOrderByMark(student_id, date);
        StudentCourseMarkDto dto = new StudentCourseMarkDto();
        dto.setId(entity.getId());
        dto.setCourseId(entity.getCourseId().getId());
        dto.setStudentId(entity.getStudentId().getId());
        dto.setMark(entity.getMark());
        dto.setCreatedDate(entity.getCreatedDate());

        return dto;
    }

    public List<StudentCourseMarkDto> getStudentCourseMarkListBetweenDates(StudentEntity studentId, LocalDate fromDate, LocalDate toDate) {
         Iterable<StudentCourseMarkEntity> iterable=    studentCourseMarkRepository.
                 findAllByStudentIdAndCreatedDateBetween(studentId, fromDate,toDate);
        List<StudentCourseMarkDto> studentDTOLinkedList = new LinkedList<>();
        iterable.forEach(entity -> {
            StudentCourseMarkDto dto = new StudentCourseMarkDto();
            dto.setId(entity.getId());
            dto.setCourseId(entity.getCourseId().getId());
            dto.setStudentId(entity.getStudentId().getId());
            dto.setMark(entity.getMark());
            dto.setCreatedDate(entity.getCreatedDate());
            studentDTOLinkedList.add(dto);
        });

        return studentDTOLinkedList;
    }

    public List<StudentCourseMarkDto> getAllStudentMark(StudentEntity studentId) {
        Iterable<StudentCourseMarkEntity> iterable=    studentCourseMarkRepository.
                findByStudentIdOrderByMarkDesc(studentId);
        List<StudentCourseMarkDto> studentDTOLinkedList = new LinkedList<>();
        iterable.forEach(entity -> {
            StudentCourseMarkDto dto = new StudentCourseMarkDto();
            dto.setId(entity.getId());
            dto.setCourseId(entity.getCourseId().getId());
            dto.setStudentId(entity.getStudentId().getId());
            dto.setMark(entity.getMark());
            dto.setCreatedDate(entity.getCreatedDate());
            studentDTOLinkedList.add(dto);
        });

        return studentDTOLinkedList;
    }

    public List<StudentCourseMarkDto> getDateMark(StudentEntity studentId, CourseEntity courseId) {
        Iterable<StudentCourseMarkEntity> iterable=    studentCourseMarkRepository.
                findByStudentIdAndCourseIdOrderByCourseIdDescMark(studentId, courseId);
        List<StudentCourseMarkDto> studentDTOLinkedList = new LinkedList<>();
        iterable.forEach(entity -> {
            StudentCourseMarkDto dto = new StudentCourseMarkDto();
            dto.setId(entity.getId());
            dto.setCourseId(entity.getCourseId().getId());
            dto.setStudentId(entity.getStudentId().getId());
            dto.setMark(entity.getMark());
            dto.setCreatedDate(entity.getCreatedDate());
            studentDTOLinkedList.add(dto);
        });

        return studentDTOLinkedList;
    }

    public StudentCourseMarkDto getFirstMark(StudentEntity id) {
        StudentCourseMarkEntity entity = studentCourseMarkRepository.findFirstByStudentIdOrderByMarkAsc(id);
        StudentCourseMarkDto dto = new StudentCourseMarkDto();
        dto.setId(entity.getId());
        dto.setCourseId(entity.getCourseId().getId());
        dto.setStudentId(entity.getStudentId().getId());
        dto.setMark(entity.getMark());
        dto.setCreatedDate(entity.getCreatedDate());

        return dto;
    }
    public StudentCourseMarkDto getLastMark(StudentEntity id) {
        StudentCourseMarkEntity entity = studentCourseMarkRepository.findFirstByStudentIdOrderByMark(id);
        StudentCourseMarkDto dto = new StudentCourseMarkDto();
        dto.setId(entity.getId());
        dto.setCourseId(entity.getCourseId().getId());
        dto.setStudentId(entity.getStudentId().getId());
        dto.setMark(entity.getMark());
        dto.setCreatedDate(entity.getCreatedDate());

        return dto;
    }

    public StudentCourseMarkDto getStudentCurseFirstMark(StudentEntity id, CourseEntity course) {
        StudentCourseMarkEntity entity = studentCourseMarkRepository.findFirstByStudentIdAndAndCourseIdOrderByMark(id,course);
        StudentCourseMarkDto dto = new StudentCourseMarkDto();
        dto.setId(entity.getId());
        dto.setCourseId(entity.getCourseId().getId());
        dto.setStudentId(entity.getStudentId().getId());
        dto.setMark(entity.getMark());
        dto.setCreatedDate(entity.getCreatedDate());

        return dto;
    }

    public Integer countCourseMark(CourseEntity course) {
        Integer count = studentCourseMarkRepository.countByCourseIdOrderByMark(course);
        return count;
    }

    public void test() {
        List<Object[]> courseObjList = studentCourseMarkRepository.findLastCourseMarkerAsNative(1);
        if (courseObjList.size() > 0) {
            Object[] courseObj = courseObjList.get(0);

            CorseDTO courseDTO = new CorseDTO();
            courseDTO.setId((Integer) courseObj[0]);
            courseDTO.setName((String) courseObj[1]);
            System.out.println(courseDTO);
        }

        System.out.println("dasda");
    }

    public void test2() {
        CourseInfoMapper courseInfoMapper = studentCourseMarkRepository.findLastCourseMarkerAsNativeMapping(1);
        if (courseInfoMapper != null) {
            CorseDTO courseDTO = new CorseDTO();
            courseDTO.setId(courseInfoMapper.getCId());
            courseDTO.setName(courseInfoMapper.getCName());
            System.out.println(courseDTO +" "+ courseInfoMapper.getMark());
        }

        System.out.println("dasda");
    }

    public Page<StudentCourseMarkDto> pagination(int page, int size){
        Pageable paging = PageRequest.of(page -1, size);
        Page<StudentCourseMarkEntity> pageObj = studentCourseMarkRepository.getAll(paging);

        long totalCount = pageObj.getTotalElements();

        List<StudentCourseMarkEntity> entityList = pageObj.getContent();
        List<StudentCourseMarkDto> dtoList = new LinkedList<>();

        for (StudentCourseMarkEntity entity : entityList){
            StudentCourseMarkDto dto = new StudentCourseMarkDto();
            dto.setId(entity.getId());
            dto.setStudentId(entity.getStudentId().getId());
            dto.setCourseId(entity.getCourseId().getId());

            dtoList.add(dto);
        }

        return new PageImpl<>(dtoList, paging, totalCount);
    }

    public Page<StudentCourseMarkDto> paginationWithStudentId(Integer id, int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "studentId");
        Pageable paging = PageRequest.of(page - 1, size, sort);
        Page<StudentCourseMarkEntity> pageObj = studentCourseMarkRepository.findAllByStudentId(id, paging);

        long totalCount = pageObj.getTotalElements();
        List<StudentCourseMarkEntity> entityList = pageObj.getContent();
        List<StudentCourseMarkDto> dtoList = new LinkedList<>();
        for (StudentCourseMarkEntity entity : entityList) {
            StudentCourseMarkDto dto = new StudentCourseMarkDto();
            dto.setId(entity.getId());
            dto.setStudentId(entity.getStudentId().getId());
            dto.setCourseId(entity.getCourseId().getId());
            dto.setMark(entity.getMark());
            dto.setCreatedDate(entity.getCreatedDate());
            dtoList.add(dto);
        }
        Page<StudentCourseMarkDto> response = new PageImpl<>(dtoList, paging, totalCount);
        return response;
    }

    public Page<StudentCourseMarkDto> paginationWithCourseId(Integer id, int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "courseId");
        Pageable paging = PageRequest.of(page - 1, size, sort);
        Page<StudentCourseMarkEntity> pageObj = studentCourseMarkRepository.findAllByCourseId(id, paging);

        long totalCount = pageObj.getTotalElements();
        List<StudentCourseMarkEntity> entityList = pageObj.getContent();
        List<StudentCourseMarkDto> dtoList = new LinkedList<>();
        for (StudentCourseMarkEntity entity : entityList) {
            StudentCourseMarkDto dto = new StudentCourseMarkDto();
            dto.setId(entity.getId());
            dto.setStudentId(entity.getStudentId().getId());
            dto.setCourseId(entity.getCourseId().getId());
            dto.setMark(entity.getMark());
            dto.setCreatedDate(entity.getCreatedDate());
            dtoList.add(dto);
        }
        Page<StudentCourseMarkDto> response = new PageImpl<>(dtoList, paging, totalCount);
        return response;
    }
}
