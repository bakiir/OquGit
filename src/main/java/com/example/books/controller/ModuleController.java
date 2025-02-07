package com.example.books.controller;

import com.example.books.model.Course;
import com.example.books.model.Module;
import com.example.books.service.CourseService;
import com.example.books.service.ModuleService;
import lombok.RequiredArgsConstructor;
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

    @DeleteMapping(value = "/{id}")
    String delete(@PathVariable Long id, Model model){

        Module module = moduleService.getOne(id);

        if (module == null || module.getCourse() == null) {
            // Если модуль не найден или не привязан к курсу, перенаправляем пользователя на страницу списка курсов
            return "redirect:/courses";
        }

        Long  courseId = moduleService.getOne(id).getCourse().getId();
        model.addAttribute("module", new Module());



        moduleService.deleteModule(id);
        List<Module> modules = moduleService.getAllByCourseId(courseId);
        model.addAttribute("modules", modules);

        return "moduleManage";

    }



}


