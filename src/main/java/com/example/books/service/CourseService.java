package com.example.books.service;

import com.example.books.model.Course;
import com.example.books.model.Module;
import com.example.books.repo.CourseRepo;
import com.example.books.repo.ModuleRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepo courseRepo;
    private final ModuleRepo moduleRepo;

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


    public void deleteCourse(Long id){
        courseRepo.deleteById(id);
    }


    public Course addModuleToCourse(Long courseId, Module module) {
        Course course = courseRepo.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        course.addModule(module);
        module.setCourse(course);

        moduleRepo.save(module);
        return courseRepo.save(course);
    }
}
