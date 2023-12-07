package com.example.lab11.Repository;

import com.example.lab11.Model.Comments;
import com.example.lab11.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<Users,Integer> {
    Users findUsersByUserId(Integer id);
//    @Query("select u from Users u where u.registrationDate>=?1 and u.registrationDate<=?2")
//    List<Users> findUsersRegisterBetween(LocalDate date1,LocalDate date2);

    @Query("select u from Users u where u.registrationDate between ?1 and ?2")
    List<Users> findUsersRegisterBetween(LocalDate date1,LocalDate date2);

    @Query("select u from Users u where u.userName=?1 and u.password=?2")
    Users findUserByUserNameAndPassword(String userName,String password);

}
