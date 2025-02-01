package com.example.books.service;


import com.example.books.model.Task;
import com.example.books.repo.LessonRepo;
import com.example.books.repo.TaskRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepo taskRepo;

    //crud

    //create
    public Task createTask(Task lesson){
        return taskRepo.save(lesson);
    }

    //read

    public List<Task> getTasks(){
        return taskRepo.findAll();
    }
    public Task getOneTask(Long id){
       return taskRepo.findById(id).orElse(new Task());
    }

   //update
    public Task updateTask(Long id, Task task){
       Task taskDb = taskRepo.findById(id).orElse(new Task());
       task.setId(taskDb.getId());
       return taskRepo.save(task);
    }


   //delete
   public void deleteTask(Long id){
        taskRepo.deleteById(id);
    }





}
