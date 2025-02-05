package com.example.books.service;

import com.example.books.model.Course;
import com.example.books.model.Module;
import com.example.books.repo.ModuleRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ModuleService {
    private final ModuleRepo moduleRepo;

    public Long getCourseIdbyId(Long id){
        Module module = moduleRepo.findById(id).orElse(new Module());
        return module.getCourse().getId();
    }

    public List<Module> getAllByCourseId(Long id){
       return moduleRepo.getAllByCourseId(id);
    }

    public List<Module> getAll(){
        return moduleRepo.findAll();
    }

    public Module getOne(Long id){
        return  moduleRepo.findById(id).orElse(new Module());
    }

    public Module addModule(Module module){
        return moduleRepo.save(module);
    }

    public Module udateCourse(Long id, Module module1){
        Module module = moduleRepo.findById(id).orElse(new Module());
        module1.setId(module.getId());
        return moduleRepo.save(module1);
    }


    public void deleteModule(Long id){
        moduleRepo.deleteById(id);
    }
}
