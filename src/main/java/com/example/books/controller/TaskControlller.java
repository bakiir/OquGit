package com.example.books.controller;

import com.example.books.model.Task;
import com.example.books.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequiredArgsConstructor
@RequestMapping("tasks")
public class TaskControlller {

    private final TaskService taskService;

    //create
    @GetMapping(value = "/create")
    String  redToCreateForm(Model model){
        model.addAttribute("task", new Task());
        return "redirect:taskCreateForm";
    }

    @PostMapping()
    String createTask(@ModelAttribute Task task){
        taskService.createTask(task);
        return "redirect:tasks";
    }

    // read
    @GetMapping()
    String getAll(Model model){
        List<Task> tasks = taskService.getTasks();
        model.addAttribute("tasks", tasks);
        return "tasks";
    }

    @GetMapping(value = "/{id}")
    String getOne(@PathVariable Long id, Model model){
        Task task = taskService.getOneTask(id);
        model.addAttribute(task);
        return "task";
    }


    //update
    @GetMapping(value = "/update/{id}")
    String  redUpdateForm(Model model, @PathVariable Long id){
        Task task = taskService.getOneTask(id);
        model.addAttribute("task", task);
        return "redirect:taskCreateForm";
    }

    @PutMapping(value = "/{id}")
    String updateTask (@ModelAttribute Task task){
        taskService.updateTask(task.getId(), task);
        return  "redirect:tasks";
    }

    //delete
    @DeleteMapping(value = "{id}")
    String deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);
        return "redirect:tasks";
    }
}
