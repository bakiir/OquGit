package com.example.books.service;


import com.example.books.model.Lesson;
import com.example.books.repo.LessonRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LessonService {

    private final LessonRepo lessonRepo;

    //create
    public Lesson createLesson(Lesson lesson){
        return lessonRepo.save(lesson);
    }

    //read

    public List<Lesson> getAllLessons(){
        return lessonRepo.findAll();
    }
   public Lesson getLessonById(Long id){
       return lessonRepo.findById(id).orElse(new Lesson());
   }

   //update
   public Lesson updateLesson(Long id, Lesson lesson){
       Lesson lessonDB = lessonRepo.findById(id).orElse(new Lesson());
       lesson.setId(lessonDB.getId());
       return lessonRepo.save(lesson);
   }


   //delete
    public void deleteLesson(Long id){
        lessonRepo.deleteById(id);
    }





}
