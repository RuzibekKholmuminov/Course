package com.example.repository;

import com.example.entity.StudentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface StudentRepository extends CrudRepository<StudentEntity, Integer>, PagingAndSortingRepository<StudentEntity, Integer> {
    StudentEntity getByName(String name);
    StudentEntity getBySurname(String surname);
    StudentEntity getByLevel(Integer level);
    StudentEntity getByAge(Integer age);
    StudentEntity getByGender(String gender);
    Page<StudentEntity> findAll( Pageable pageable);
    Page<StudentEntity> findAllByLevelOrderById(Integer level, Pageable pageable);
    Page<StudentEntity> findAllByGender(String gender, Pageable pageable);
}
