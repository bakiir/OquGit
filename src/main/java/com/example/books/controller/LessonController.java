package com.example.books.controller;

import com.example.books.model.Course;
import com.example.books.model.Lesson;
import com.example.books.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/lesson")
public class LessonController {

    private final LessonService lessonService;

    //create
    @GetMapping(value = "/create")
    String  redToCreateForm(Model model){
        model.addAttribute("lesson", new Lesson());
        return "redirect:lessonCreateForm";
    }

    @PostMapping()
    String createLesson(@ModelAttribute Lesson lesson){
        lessonService.createLesson(lesson);
        return "redirect:course";
    }

    // read
    @GetMapping()
    List<Lesson> getAll(Model model){
        List<Lesson> lessons = lessonService.getLessons();
        model.addAttribute("lessons", lessons);
        return lessons;
    }

    @GetMapping(value = "/{id}")
    String getOne(@PathVariable Long id, Model model){
        Lesson lesson = lessonService.getOneLesson(id);
        model.addAttribute(lesson);
        return "lesson";
    }


    //update
    @GetMapping(value = "/update/{id}")
    String  redUpdateForm(Model model, @PathVariable Long id){
        Lesson lesson = lessonService.getOneLesson(id);
        model.addAttribute("lesson", lesson);
        return "redirect:lessonCreateForm";
    }

    @PutMapping(value = "/{id}")
    String updateCourse (@ModelAttribute Lesson lesson){
        lessonService.updateLesson(lesson.getId(), lesson);
        return  "redirect:lesson";
    }

    //delete
    @DeleteMapping(value = "{id}")
    String deleteCourse(@PathVariable Long id){
        lessonService.deleteLesson(id);
        return "redirect:course";
    }
}
