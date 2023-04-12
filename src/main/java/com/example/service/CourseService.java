package com.example.service;

import com.example.dto.CourseDto;
import com.example.entity.CourseEntity;
import com.example.exp.AppBadRequestException;
import com.example.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    public CourseDto create(CourseDto dto) {
        CourseEntity entity = new CourseEntity();
        entity.setName(dto.getName());
        if (dto.getName() == null || dto.getName().isBlank()) {
            throw new AppBadRequestException("Name qani?");
        }

        entity.setPrice(dto.getPrice());
        if (dto.getPrice() == null ) {
            throw new AppBadRequestException("Price qani?");
        }

        entity.setDuration(dto.getDuration());
        if (dto.getDuration() == null|| dto.getDuration().isBlank()){
            throw new AppBadRequestException("Duration qani?");
        }

        entity.setCreated_date(dto.getCreated_date());
        courseRepository.save(entity);
        dto.setId(entity.getId());
        return dto;
    }

    public List<CourseDto> getAll() {
        Iterable<CourseEntity> iterable = courseRepository.findAll();
        List<CourseDto> studentDTOLinkedList = new LinkedList<>();
        iterable.forEach(entity -> {
            CourseDto dto = new CourseDto();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setPrice(entity.getPrice());
            dto.setDuration(entity.getDuration());
            dto.setCreated_date(entity.getCreated_date());
            studentDTOLinkedList.add(dto);
        });
        return studentDTOLinkedList;
    }

    public CourseDto getById(Integer id) {
        CourseEntity entity = get(id);
        CourseDto dto = new CourseDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setPrice(entity.getPrice());
        dto.setDuration(entity.getDuration());
        dto.setCreated_date(entity.getCreated_date());

        return dto;
    }

    public boolean update(Integer id, CourseDto dto) {
        CourseEntity entity = get(id);
        entity.setName(dto.getName());
        entity.setPrice(dto.getPrice());
        entity.setDuration(dto.getDuration());
        entity.setCreated_date(dto.getCreated_date());
        courseRepository.save(entity);
        return true;
    }

    public boolean delete(Integer id) {
        CourseEntity entity = get(id);
        courseRepository.delete(entity);
        return true;
    }

    public CourseEntity get(Integer id) {
        Optional<CourseEntity> entity = courseRepository.findById(id);
        if (entity.isEmpty()) {
            throw new AppBadRequestException("Student not found: " + id);
        }
        return entity.get();
    }

    public CourseDto getByName(String name) {
        CourseEntity entity = courseRepository.getByName(name);
        if (entity == null){
            throw new AppBadRequestException("Course not found: " + name);
        }
        CourseDto dto = new CourseDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setPrice(entity.getPrice());
        dto.setDuration(entity.getDuration());
        dto.setCreated_date(entity.getCreated_date());
        return dto;
    }

    public CourseDto getByPrice(Integer price) {
        CourseEntity entity = courseRepository.getByPrice(price);
        if (entity == null){
            throw new AppBadRequestException("Price not found: " + price);
        }
        CourseDto dto = new CourseDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setPrice(entity.getPrice());
        dto.setDuration(entity.getDuration());
        dto.setCreated_date(entity.getCreated_date());
        return dto;
    }

    public CourseDto getByDuration(String duration) {
        CourseEntity entity = courseRepository.getByDuration(duration);
        if (entity == null){
            throw new AppBadRequestException("Duration not found: " + duration);
        }
        CourseDto dto = new CourseDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setPrice(entity.getPrice());
        dto.setDuration(entity.getDuration());
        dto.setCreated_date(entity.getCreated_date());
        return dto;
    }

    public Page<CourseDto> pagination(int page, int size){
        Pageable paging = PageRequest.of(page -1, size);
        Page<CourseEntity> pageObj = courseRepository.getAll(paging);

        long totalCount = pageObj.getTotalElements();

        List<CourseEntity> entityList = pageObj.getContent();
        List<CourseDto> dtoList = new LinkedList<>();

        for (CourseEntity entity : entityList){
            CourseDto dto = new CourseDto();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dtoList.add(dto);
        }

        return new PageImpl<>(dtoList, paging, totalCount);
    }

    public Page<CourseDto> paginationByCreatedDate(int page, int size){
        Pageable paging = PageRequest.of(page -1, size);
        Page<CourseEntity> pageObj = courseRepository.getBy(paging);

        long totalCount = pageObj.getTotalElements();

        List<CourseEntity> entityList = pageObj.getContent();
        List<CourseDto> dtoList = new LinkedList<>();

        for (CourseEntity entity : entityList){
            CourseDto dto = new CourseDto();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dtoList.add(dto);
        }

        return new PageImpl<>(dtoList, paging, totalCount);
    }

    public Page<CourseDto> paginationByPrice(Integer price, int page, int size){
        Pageable paging = PageRequest.of(page -1, size);
        Page<CourseEntity> pageObj = courseRepository.getByPrice(price,paging);

        long totalCount = pageObj.getTotalElements();

        List<CourseEntity> entityList = pageObj.getContent();
        List<CourseDto> dtoList = new LinkedList<>();

        for (CourseEntity entity : entityList){
            CourseDto dto = new CourseDto();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setPrice(entity.getPrice());
            dtoList.add(dto);
        }

        return new PageImpl<>(dtoList, paging, totalCount);
    }
}
