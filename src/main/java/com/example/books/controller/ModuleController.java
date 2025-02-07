package com.example.books.controller;

import com.example.books.model.Course;
import com.example.books.model.Lesson;
import com.example.books.model.Module;
import com.example.books.service.CourseService;
import com.example.books.service.ModuleService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/modules")
public class ModuleController {
    private final ModuleService moduleService;
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
        return "moduleManage";
    }



}


