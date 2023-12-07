package com.example.lab11.Service;

import com.example.lab11.Api.ApiException;
import com.example.lab11.Model.Users;
import com.example.lab11.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UsersService {
    private final UserRepository userRepository;

    public List<Users>getAllUsers(){
        return userRepository.findAll();
    }
    public void addUser(Users user){
        userRepository.save(user);
    }
    public void updateUser(Integer id,Users user){
        Users oldUser=userRepository.findUsersByUserId(id);
        if(oldUser==null){
            throw new ApiException("user not found");
        }
        oldUser.setUserName(user.getUserName());
        oldUser.setPassword(user.getPassword());
        oldUser.setRegistrationDate(user.getRegistrationDate());

        userRepository.save(oldUser);
    }
    public void deleteUser(Integer id){
        Users user=userRepository.findUsersByUserId(id);
        if(user==null){
            throw new ApiException("user not found");
        }
        userRepository.delete(user);
    }
    public List<Users> userRegDate(LocalDate date1,LocalDate date2){
        List<Users>usersRegDate=userRepository.findUsersRegisterBetween(date1, date2);
        if(usersRegDate.isEmpty()){
            throw new ApiException("no users register in this range");
        }
        return usersRegDate;
    }
    public Users findUserByUP(String userName,String password){
        Users user=userRepository.findUserByUserNameAndPassword(userName, password);
        if(user==null){
            throw new ApiException("not found users");
        }
        return user;
    }
}
