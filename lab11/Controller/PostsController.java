package com.example.lab11.Controller;

import com.example.lab11.Model.Posts;
import com.example.lab11.Service.PostsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class PostsController {
    private final PostsService postsService;
    @GetMapping("/get")
    public ResponseEntity getAllPosts(){
        return ResponseEntity.status(200).body(postsService.getAllPosts());
    }
    @PostMapping("/add")
    public ResponseEntity addPost(@RequestBody@Valid Posts post , Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        postsService.addPost(post);
        return ResponseEntity.status(200).body("post added!");
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updatePost(@PathVariable Integer id,@RequestBody@Valid Posts post,Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        postsService.updatePost(id, post);
        return ResponseEntity.status(200).body("post updated");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deletePost(@PathVariable Integer id){
        postsService.deletePost(id);
        return ResponseEntity.status(200).body("post deleted");
    }
    //1
    @GetMapping("/get/b{id}")
    public ResponseEntity getPostsByUser(@PathVariable Integer id){
        return ResponseEntity.status(200).body(postsService.getAllByUserId(id));
    }
    //2
    @GetMapping("/get/by{title}")
    public ResponseEntity getPostByTitle(@PathVariable String title){
        return ResponseEntity.status(200).body(postsService.getAllByTitle(title));
    }
    //4
    @GetMapping("/get/d{date}")
    public ResponseEntity getByBeforeDate(@PathVariable LocalDate date){
        return ResponseEntity.status(200).body(postsService.getPostsByBeforeDate(date));
    }
}
