package com.example.repository;

import com.example.entity.CourseEntity;
import org.springframework.data.repository.CrudRepository;

public interface CourseRepository extends CrudRepository<CourseEntity, Integer> {
    CourseEntity getByName(String name);
    CourseEntity getByPrice(Integer price);
    CourseEntity getByDuration(String duration);
}
