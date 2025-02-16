package com.example.books.repo;

import com.example.books.model.Course;
import com.example.books.model.Lesson;
import com.example.books.model.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModuleRepo extends JpaRepository<Module, Long> {
    List<Module> getAllByCourseId(Long id);


}
