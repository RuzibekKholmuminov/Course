package com.example.service;

import com.example.dto.StudentDto;
import com.example.entity.StudentEntity;
import com.example.exp.AppBadRequestException;
import com.example.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
    public StudentDto create(StudentDto dto) {
        StudentEntity entity = new StudentEntity();
        entity.setName(dto.getName());
        if (dto.getName() == null || dto.getName().isBlank()) {
            throw new AppBadRequestException("Name qani?");
        }

        entity.setSurname(dto.getSurname());
        if (dto.getSurname() == null || dto.getSurname().isBlank()) {
            throw new AppBadRequestException("Surname qani?");
        }

        entity.setAge(dto.getAge());
        if (dto.getAge() == null){
            throw new AppBadRequestException("Age qani?");
        }

        entity.setLevel(dto.getLevel());
        if (dto.getLevel() == null){
            throw new AppBadRequestException("Level qani?");
        }

        entity.setGender(dto.getGender());
        entity.setCreated_date(dto.getCreated_date());
        studentRepository.save(entity);
        dto.setId(entity.getId());
        return dto;
    }

    public List<StudentDto> getAll() {
        Iterable<StudentEntity> iterable = studentRepository.findAll();
        List<StudentDto> studentDTOLinkedList = new LinkedList<>();
        iterable.forEach(entity -> {
            StudentDto dto = new StudentDto();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setSurname(entity.getSurname());
            dto.setAge(entity.getAge());
            dto.setLevel(entity.getLevel());
            dto.setGender(entity.getGender());
            dto.setCreated_date(entity.getCreated_date());
            studentDTOLinkedList.add(dto);
        });
        return studentDTOLinkedList;
    }

    public StudentDto getById(Integer id) {
        StudentEntity entity = get(id);
        StudentDto dto = new StudentDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setSurname(entity.getSurname());
        dto.setAge(entity.getAge());
        dto.setLevel(entity.getLevel());
        dto.setCreated_date(entity.getCreated_date());
        dto.setGender(entity.getGender());

        return dto;
    }

    public boolean update(Integer id, StudentDto studentDTO) {
        StudentEntity entity = get(id);
        entity.setName(studentDTO.getName());
        entity.setSurname(studentDTO.getSurname());
        entity.setAge(studentDTO.getAge());
        entity.setLevel(studentDTO.getLevel());
        entity.setCreated_date(studentDTO.getCreated_date());
        entity.setGender(studentDTO.getGender());
        studentRepository.save(entity);
        return true;
    }

    public boolean delete(Integer id) {
        StudentEntity entity = get(id);
        studentRepository.delete(entity);
        return true;
    }

    public StudentDto getByName(String name) {
        StudentEntity entity = studentRepository.getByName(name);
        if (entity == null){
            throw new AppBadRequestException("Student not found: " + name);
        }
        StudentDto dto = new StudentDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setSurname(entity.getSurname());
        dto.setAge(entity.getAge());
        dto.setLevel(entity.getLevel());
        dto.setCreated_date(entity.getCreated_date());
        dto.setGender(entity.getGender());
        return dto;
    }

    public StudentEntity get(Integer id) {
        Optional<StudentEntity> entity = studentRepository.findById(id);
        if (entity.isEmpty()) {
            throw new AppBadRequestException("Student not found: " + id);
        }
        return entity.get();
    }

    public StudentDto getBySurname(String surname) {
        StudentEntity entity = studentRepository.getBySurname(surname);
        if (entity == null){
            throw new AppBadRequestException("Student not found: " + surname);
        }
        StudentDto dto = new StudentDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setSurname(entity.getSurname());
        dto.setAge(entity.getAge());
        dto.setLevel(entity.getLevel());
        dto.setCreated_date(entity.getCreated_date());
        dto.setGender(entity.getGender());
        return dto;
    }

    public StudentDto getByLevel(Integer level) {
        StudentEntity entity = studentRepository.getByLevel(level);
        if (entity == null){
            throw new AppBadRequestException("Level not found: " + level);
        }
        StudentDto dto = new StudentDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setSurname(entity.getSurname());
        dto.setAge(entity.getAge());
        dto.setLevel(entity.getLevel());
        dto.setCreated_date(entity.getCreated_date());
        dto.setGender(entity.getGender());
        return dto;
    }

    public StudentDto getByAge(Integer age) {
        StudentEntity entity = studentRepository.getByAge(age);
        if (entity == null){
            throw new AppBadRequestException("Age not found: " + age);
        }
        StudentDto dto = new StudentDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setSurname(entity.getSurname());
        dto.setAge(entity.getAge());
        dto.setLevel(entity.getLevel());
        dto.setCreated_date(entity.getCreated_date());
        dto.setGender(entity.getGender());
        return dto;
    }

    public StudentDto getByGender(String gender) {
        StudentEntity entity = studentRepository.getByGender(gender);
        if (entity == null){
            throw new AppBadRequestException("Gender not found: " + gender);
        }
        StudentDto dto = new StudentDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setSurname(entity.getSurname());
        dto.setAge(entity.getAge());
        dto.setLevel(entity.getLevel());
        dto.setCreated_date(entity.getCreated_date());
        dto.setGender(entity.getGender());
        return dto;
    }

    public Page<StudentDto> pagination(int page, int size){
        Pageable paging = PageRequest.of(page -1, size);
        Page<StudentEntity> pageObj = studentRepository.findAll(paging);

        long totalCount = pageObj.getTotalElements();

        List<StudentEntity> entityList = pageObj.getContent();
        List<StudentDto> dtoList = new LinkedList<>();

        for (StudentEntity entity : entityList){
            StudentDto dto = new StudentDto();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setSurname(entity.getSurname());
            dtoList.add(dto);
        }

        return new PageImpl<>(dtoList, paging, totalCount);
    }

    public Page<StudentDto> paginationWithLevel(Integer level, int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "level");
        Pageable paging = PageRequest.of(page - 1, size, sort);
        Page<StudentEntity> pageObj = studentRepository.findAllByLevelOrderById(level, paging);

        long totalCount = pageObj.getTotalElements();
        List<StudentEntity> entityList = pageObj.getContent();
        List<StudentDto> dtoList = new LinkedList<>();
        for (StudentEntity entity : entityList) {
            StudentDto dto = new StudentDto();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setSurname(entity.getSurname());
            dtoList.add(dto);
        }
        Page<StudentDto> response = new PageImpl<StudentDto>(dtoList, paging, totalCount);
        return response;
    }

    public Page<StudentDto> paginationWithGender(String gender, int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "gender");
        Pageable paging = PageRequest.of(page - 1, size, sort);
        Page<StudentEntity> pageObj = studentRepository.findAllByGender(gender, paging);

        long totalCount = pageObj.getTotalElements();
        List<StudentEntity> entityList = pageObj.getContent();
        List<StudentDto> dtoList = new LinkedList<>();
        for (StudentEntity entity : entityList) {
            StudentDto dto = new StudentDto();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setSurname(entity.getSurname());
            dtoList.add(dto);
        }
        Page<StudentDto> response = new PageImpl<>(dtoList, paging, totalCount);
        return response;
    }



}
