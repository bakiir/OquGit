package com.example.books.controller;

import com.example.books.model.Category;
import com.example.books.model.Course;
import com.example.books.model.Module;
import com.example.books.service.CategoryService;
import com.example.books.service.CourseService;
import com.example.books.service.ModuleService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;
    private final CategoryService CategoryService;
    private final ModuleService moduleService;
    private final Logger logger = LoggerFactory.getLogger(CourseController.class);



    //create
    @GetMapping(value = "/create")
    String  redToCreateForm(Model model){
        List<Category> categories = CategoryService.getall();
        model.addAttribute("course", new Course());
        model.addAttribute("categories", categories);
        return "courseCreateForm";
    }

    @PostMapping("/create")
    String createCourse(@ModelAttribute Course course){
        courseService.createCourse(course);
        return "redirect:/courses";
    }

    // read
    @GetMapping()
    String getAll(Model model){
        List<Course> courses = courseService.getAll();
        model.addAttribute("cources", courses);
        return "catalog";
    }

    @GetMapping(value = "/{id}")
    String getOne(@PathVariable Long id, Model model){
        Course course = courseService.getOne(id);
        List<Module> modules = moduleService.getAllByCourseId(id);
        model.addAttribute("modules", modules);
        model.addAttribute("course",course);
        return "course";
    }


    //update
    @GetMapping(value = "/update/{id}")
    String  redUpdateForm(Model model, @PathVariable Long id){
        Course course = courseService.getOne(id);
        model.addAttribute("course", course);
        return "redirect:courseCreateForm";
    }

    @PutMapping(value = "/{id}")
    String updateCourse (@ModelAttribute Course course){
        courseService.udateCourse(course.getId(), course);
        return  "redirect:course";
    }

    //delete
    @DeleteMapping(value = "{id}")
    String deleteCourse(@PathVariable Long id){
        courseService.deleteCourse(id);
        return "catalog";
    }



    @PostMapping(value = "/{id}/addModule")
    String addModule(@PathVariable Long id, @ModelAttribute Module module){
        Course course = courseService.getOne(id);
        Module mod = new Module();
        mod.setCourse(course);
        mod.setName(module.getName());
        moduleService.addModule(mod);
        return "redirect:/courses/"+id;
    }

    @GetMapping(value = "/{id}/modules")
    String addModule(@PathVariable Long id, Model model){
        List<Module> modules = moduleService.getAllByCourseId(id);
        model.addAttribute("module", new Module());
        model.addAttribute("modules", modules);
        return "moduleManage";
    }





}
