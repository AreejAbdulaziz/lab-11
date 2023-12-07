package com.example.lab11.Repository;

import com.example.lab11.Model.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CommentsRepository extends JpaRepository<Comments,Integer> {
    Comments findCommentsByCommentId(Integer id);
    @Query("select c from Comments c where c.postId=?1 ") //u obj not table
    List<Comments> findCommentsByPostId(Integer id);
    @Query("select c from Comments c where c.commentDate >?1 ")
    List<Comments> findCommentsAfterDate(LocalDate date);
}
