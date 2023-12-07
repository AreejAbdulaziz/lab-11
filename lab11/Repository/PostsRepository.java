package com.example.lab11.Repository;

import com.example.lab11.Model.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface PostsRepository extends JpaRepository<Posts,Integer> {
    Posts findPostsByPostId(Integer id);
    List<Posts> findPostsByUserId(Integer id);
    List<Posts> findPostsByTitle(String title);
    List<Posts> getPostsByPublicationDateIsBefore(LocalDate date);
}
