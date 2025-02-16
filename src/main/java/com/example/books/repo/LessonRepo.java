package com.example.books.repo;

import com.example.books.model.Lesson;
import com.example.books.model.Module;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonRepo extends JpaRepository<Lesson, Long> {
    List<Lesson> findAllByModule(Module module);
}
