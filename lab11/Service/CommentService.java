package com.example.lab11.Service;

import com.example.lab11.Api.ApiException;
import com.example.lab11.Model.Comments;
import com.example.lab11.Model.Posts;
import com.example.lab11.Model.Users;
import com.example.lab11.Repository.CommentsRepository;
import com.example.lab11.Repository.PostsRepository;
import com.example.lab11.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentsRepository commentsRepository;
    private final PostsRepository postsRepository;
    private final UserRepository userRepository;
    public List<Comments> getAllComments(){
        return commentsRepository.findAll();
    }
    public void addComment(Comments comment){
        Posts post=postsRepository.findPostsByPostId(comment.getPostId());
        if(post==null){
            throw new ApiException("post id not found");
        }
        Users user=userRepository.findUsersByUserId(comment.getUserId());
        if(user==null){
            throw new ApiException("user id not found");
        }
        commentsRepository.save(comment);
    }
    public void updateComment(Integer id,Comments comment1){
        Comments oldComment=commentsRepository.findCommentsByCommentId(id);
        if(oldComment==null){
            throw new ApiException("comment id not found");
        }
        Posts post2=postsRepository.findPostsByPostId(comment1.getPostId());
        if(post2==null){
            throw new ApiException("post id not found");
        }
        Users user2=userRepository.findUsersByUserId(comment1.getUserId());
        if(user2==null){
            throw new ApiException("user id not found");
        }
        oldComment.setPostId(comment1.getPostId());
        oldComment.setUserId(comment1.getUserId());
        oldComment.setContent(comment1.getContent());
        oldComment.setCommentDate(comment1.getCommentDate());

        commentsRepository.save(oldComment);
    }
    public void deleteComment(Integer id){
        Comments comment=commentsRepository.findCommentsByCommentId(id);
        if(comment==null){
            throw new ApiException("comment id not found");
        }
        commentsRepository.delete(comment);
    }
    public List<Comments> commentsByPostId(Integer id){
        Posts post=postsRepository.findPostsByPostId(id);
        if(post==null){
            throw new ApiException("wrong post id");
        }
        List<Comments>commentsP=commentsRepository.findCommentsByPostId(id);
        if(commentsP.isEmpty()){
            throw new ApiException("no comments on post");
        }
        return commentsP;
    }
    public List<Comments>commentsAfterDate(LocalDate date){
        List<Comments>commentsAfterDate=commentsRepository.findCommentsAfterDate(date);
        if(commentsAfterDate.isEmpty()){
            throw new ApiException("no new comments");
        }
        return commentsAfterDate;
    }
}
