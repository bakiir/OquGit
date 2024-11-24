package com.example.books.service;

import com.example.books.model.Course;
import com.example.books.repo.CourseRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepo courseRepo;

    public List<Course> getAll(){
        return  courseRepo.findAll();
    }

    public Course getOne(Long id){
        return  courseRepo.findById(id).orElse(new Course());
    }

    public Course createCourse(Course course){
        return courseRepo.save(course);
    }

    public Course udateCourse(Long id, Course course){
        Course courseDB = courseRepo.findById(id).orElse(new Course());
        course.setId(courseDB.getId());
        return courseRepo.save(course);
    }

    public boolean publish(Long id){
         Course course = courseRepo.findById(id).orElse(new Course());
         course.setPublished(true);
         return true;
    }

    public void deleteCourse(Long id){
        courseRepo.deleteById(id);
    }
}
