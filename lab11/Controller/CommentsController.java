package com.example.lab11.Controller;

import com.example.lab11.Model.Comments;
import com.example.lab11.Service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/comments")
@RequiredArgsConstructor
public class CommentsController {
    private final CommentService commentService;
    @GetMapping("/get")
    public ResponseEntity getAllComments(){
        return ResponseEntity.status(200).body(commentService.getAllComments());
    }
    @PostMapping("/add")
    public ResponseEntity addComment(@RequestBody@Valid Comments comment, Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        commentService.addComment(comment);
        return ResponseEntity.status(200).body("comment added");
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateComment(@PathVariable Integer id,@RequestBody@Valid Comments comment,Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        commentService.updateComment(id,comment);
        return ResponseEntity.status(200).body("comment updated");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteComment(@PathVariable Integer id){
        commentService.deleteComment(id);
        return ResponseEntity.status(200).body("comment deleted");
    }
    //3
    @GetMapping("/get/{id}")
    public ResponseEntity commentsByPostId(@PathVariable Integer id){
        return ResponseEntity.status(200).body(commentService.commentsByPostId(id));
    }
    //5
    @GetMapping("/get/d{date}")
    public ResponseEntity commentsAfterDate(@PathVariable LocalDate date){
        return ResponseEntity.status(200).body(commentService.commentsAfterDate(date));
    }
}
