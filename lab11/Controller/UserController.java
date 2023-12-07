package com.example.lab11.Controller;

import com.example.lab11.Model.Users;
import com.example.lab11.Service.UsersService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UsersService usersService;
    @GetMapping("/get")
    public ResponseEntity getAllUsers(){
        return ResponseEntity.status(200).body(usersService.getAllUsers());
    }
    @PostMapping("/add")
    public ResponseEntity addUser(@RequestBody@Valid Users user, Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        usersService.addUser(user);
        return ResponseEntity.status(200).body("user added!");
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@PathVariable Integer id,@RequestBody@Valid Users user,Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        usersService.updateUser(id, user);
        return ResponseEntity.status(200).body("user updated!");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id){
        usersService.deleteUser(id);
        return ResponseEntity.status(200).body("user deleted!");
    }
    //6
    @GetMapping("/get/r{date1}/{date2}")
    public ResponseEntity userRegDate(@PathVariable LocalDate date1,@PathVariable LocalDate date2){
        return ResponseEntity.status(200).body(usersService.userRegDate(date1, date2));
    }
    //7
    @GetMapping("/get/b{userName}/{password}")
    public ResponseEntity findUserUP(@PathVariable String userName,@PathVariable String password){
        return ResponseEntity.status(200).body(usersService.findUserByUP(userName,password));
    }
}
