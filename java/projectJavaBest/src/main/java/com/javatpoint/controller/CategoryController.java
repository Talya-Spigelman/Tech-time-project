package com.javatpoint.controller;

import com.javatpoint.model.Category;
import com.javatpoint.model.Post;
import com.javatpoint.model.User;
import com.javatpoint.service.CategoryRepository;
import com.javatpoint.service.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@CrossOrigin
@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryController(CategoryRepository categoryRepository) {this.categoryRepository = categoryRepository;}

    //פונקציה שמראה את כל הקטגוריות
    @GetMapping("/get")
    public ResponseEntity<List<Category>> getCategory(){
        try {
            List<Category> categories= new ArrayList<>();
            categoryRepository.findAll().forEach(e->categories.add(e));
            return new ResponseEntity<>(categories, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //פונקציה שמראה קטגוריה לפי ID ספציפי
    @GetMapping("/get/{id}")
    public ResponseEntity<Category> getCategory(@PathVariable long id){
        Category c = categoryRepository.findById(id).orElse(null);
        if(c != null){
            return new ResponseEntity<>(c,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //פונקציה שיוצרת קטגוריה
    @PostMapping("/create")
    public ResponseEntity<Category> createCategory(@RequestBody Category c){
        try{
            Category newCategory=categoryRepository.save(c);
            return new ResponseEntity<>(newCategory,HttpStatus.CREATED);

        }
        catch (Exception e){
            System.out.println(e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //פונקציית מיון
    //לפי ID
    @GetMapping ("/categoryById")
    public ResponseEntity categoryById(@RequestBody Category c){
        Category c1=categoryRepository.findAllById(c.getId());
        return new ResponseEntity<>(c1, HttpStatus.OK);
    }
}


