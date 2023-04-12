package com.example.repository;

import com.example.entity.CourseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CourseRepository extends CrudRepository<CourseEntity, Integer> {
    CourseEntity getByName(String name);
    CourseEntity getByPrice(Integer price);
    CourseEntity getByDuration(String duration);
    Page<CourseEntity> getAll(Pageable pageable);

    Page<CourseEntity> getByPrice(Integer price, Pageable pageable);


}
