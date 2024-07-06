package com.example.workmannager.services;

import com.example.workmannager.model.Category;
import com.example.workmannager.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    public ResponseEntity<HttpStatus> getAllCategorys(){
        return new ResponseEntity(categoryRepository.findAll().stream().toList(), HttpStatus.OK);
    }
    public ResponseEntity<HttpStatus> saveCategory(Category category){
        return new ResponseEntity(categoryRepository.save(category), HttpStatus.OK);
    }
    public ResponseEntity<HttpStatus> deleteCategoryById(Integer id){
        categoryRepository.deleteById(id.longValue());
        return new ResponseEntity( "ok", HttpStatus.OK);
    }
}
