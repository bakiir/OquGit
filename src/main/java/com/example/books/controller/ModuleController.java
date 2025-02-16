package com.example.books.controller;

import com.example.books.model.Course;
import com.example.books.model.Lesson;
import com.example.books.model.Module;
import com.example.books.service.CourseService;
import com.example.books.service.LessonService;
import com.example.books.service.ModuleService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.cert.CertificateException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/modules")
public class ModuleController {
    private final ModuleService moduleService;
    private final LessonService lessonService;
    private final Logger logger =  LoggerFactory.getLogger(ModuleService.class);



    @DeleteMapping(value = "/{id}")
    String delete(@PathVariable Long id, Model model){

        Module module = moduleService.getOne(id);

        if (module == null || module.getCourse() == null) {
            return "redirect:/courses";
        }

        Long  courseId = moduleService.getOne(id).getCourse().getId();
        logger.info(courseId.toString());
        model.addAttribute("module", new Module());

        moduleService.deleteModule(id);
        List<Module> modules = moduleService.getAllByCourseId(courseId);
        model.addAttribute("modules", modules);

        return "redirect:/courses/"+courseId;

    }

    @GetMapping(value = "/{id}")
    String addLessonToModule(@PathVariable Long id, Model model){
        Module module = moduleService.getOne(id);
        model.addAttribute("module", module);
        model.addAttribute("lesson", new Lesson());
        List<Lesson> lessons = lessonService.getLessonsByModule(id);
        model.addAttribute("lessons", lessons);
        return "moduleManage";
    }

    @PostMapping("/{id}/addLesson")
    public String addLesson(@PathVariable Long id,
                            @RequestParam("file") MultipartFile file,
                            @RequestParam("title") String title,
                            Model model) {
        try {


            String fileUrl = lessonService.saveLesson(file,title, moduleService.getOne(id));

            Lesson lesson = new Lesson();
            lesson.setTitle(title);
            lesson.setURL(fileUrl);

            // Устанавливаем module перед сохранением
            Module module = moduleService.getOne(id);
            if (module == null) {
                throw new RuntimeException("Модуль с ID " + id + " не найден!");
            }
            lesson.setModule(module);


            return "redirect:/modules/" + id;
        } catch (Exception e) {
            model.addAttribute("error", "Ошибка загрузки файла: " + e.getMessage());
            return "error";
        }
    }






}


