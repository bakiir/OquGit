package com.example.books.controller;

import com.example.books.model.Category;
import com.example.books.model.Course;
import com.example.books.service.CategoryService;
import com.example.books.service.CourseService;
import lombok.RequiredArgsConstructor;
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
        model.addAttribute(course);
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
        return "redirect:catalog";
    }








}
