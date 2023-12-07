package com.example.lab11.Controller;

import com.example.lab11.Model.Categories;
import com.example.lab11.Service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    @GetMapping("/get")
    public ResponseEntity getAllCategories(){
        return ResponseEntity.status(200).body(categoryService.getAllCategories());
    }
    @PostMapping("/add")
    public ResponseEntity addCategory(@RequestBody@Valid Categories category, Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        categoryService.addCategory(category);
        return ResponseEntity.status(200).body("category added!");
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateCategory(@PathVariable Integer id,@RequestBody@Valid Categories category,Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        categoryService.updateCategory(id, category);
        return ResponseEntity.status(200).body("category updated!");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCategory(@PathVariable Integer id){
        categoryService.deleteCategory(id);
        return ResponseEntity.status(200).body("category deleted");
    }
    //7
    @GetMapping("/get/bc{category}")
    public ResponseEntity findPostsIdByCat(@PathVariable String category){
        return ResponseEntity.status(200).body(categoryService.findPostsIdByCat(category));
    }
}
