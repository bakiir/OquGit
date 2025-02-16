package com.example.books.service;


import com.example.books.model.Lesson;
import com.example.books.model.Module;
import com.example.books.repo.LessonRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LessonService {


    private final LessonRepo lessonRepo;
    private final ModuleService moduleService;

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


    public List<Lesson> getLessonsByModule(Long id){
        Module module = moduleService.getOne(id);
        List<Lesson> lessons = lessonRepo.findAllByModule(module);
        return lessons;
    }


    @Value("${file.upload-dir}")
    private String uploadDir;

    public String saveLesson(MultipartFile file, String title, Module module) {
        try {
            // Создаём директорию, если её нет
            Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // Генерируем уникальное имя файла
            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path filePath = uploadPath.resolve(fileName);

            // Копируем файл в папку uploadDir
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            // Относительный путь к файлу
            String fileUrl = "uploads/" + fileName;

            // Создаём урок и привязываем его к модулю
            Lesson lesson = new Lesson();
            lesson.setTitle(title);
            lesson.setURL(fileUrl);
            lesson.setModule(module); // 🟢 Устанавливаем модуль

            lessonRepo.save(lesson);

            return fileUrl;
        } catch (IOException e) {
            throw new RuntimeException("Ошибка загрузки файла", e);
        }
    }








}
