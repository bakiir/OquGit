package com.example.books.service;

import com.example.books.model.Category;
import com.example.books.repo.CategoryRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepo categoryRepo;

    public Category create (Category category){
        return categoryRepo.save(category);
    }

    public List<Category> getall(){
       return categoryRepo.findAll();
    }

    public Category getById(Long id){
        return categoryRepo.findById(id).orElse(new Category());
    }

    public Category update (Category category, Long id ){
        category.setId(id);
        return categoryRepo.save(category);
    }

    public void delete(Long id){
        categoryRepo.deleteById(id);
    }


}
