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
        List<Category> categories = CategoryService.getallCategories();
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

    @GetMapping("/{courseId}")
    public String getCourse(@PathVariable Long courseId, Model model) {
        Course course = courseService.getOne(courseId);
        model.addAttribute("course", course);
        model.addAttribute("module", new Module()); // For form submission
        return "course";
    }




    //delete
    @DeleteMapping(value = "{id}")
    String deleteCourse(@PathVariable Long id){
        courseService.deleteCourse(id);
        return "catalog";
    }



    @PostMapping("/{courseId}/modules")
    public String addModule(@PathVariable Long courseId,
                            @ModelAttribute Module module
    ) {
        Course updatedCourse = courseService.addModuleToCourse(courseId, module);
        return "redirect:/courses/" + courseId;

    }

    @GetMapping(value = "/{id}/modules")
    String addModule(@PathVariable Long id, Model model){
        List<Module> modules = moduleService.getAllByCourseId(id);
        model.addAttribute("newModule", new Module());
        model.addAttribute("modules", modules);
        return "moduleManage";
    }





}
