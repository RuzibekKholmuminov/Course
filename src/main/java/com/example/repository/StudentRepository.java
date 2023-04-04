package com.example.repository;

import com.example.entity.StudentEntity;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<StudentEntity, Integer> {

    StudentEntity getByName(String name);
    StudentEntity getBySurname(String surname);
    StudentEntity getByLevel(Integer level);
    StudentEntity getByAge(Integer age);
    StudentEntity getByGender(String gender);

}
