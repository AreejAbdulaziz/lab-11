package com.example.lab11.Service;

import com.example.lab11.Api.ApiException;
import com.example.lab11.Model.Posts;
import com.example.lab11.Model.Users;
import com.example.lab11.Repository.PostsRepository;
import com.example.lab11.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostsService {
    private final PostsRepository postsRepository;
    private final UserRepository userRepository;
    public List<Posts>getAllPosts(){
        return postsRepository.findAll();
    }
    public void addPost(Posts post){
        Users user=userRepository.findUsersByUserId(post.getUserId());
        if(user==null){
            throw new ApiException("wrong user id");
        }
        postsRepository.save(post);
    }
    public void updatePost(Integer id,Posts post){
        Posts oldPost=postsRepository.findPostsByPostId(id);
        if(oldPost==null){
            throw new ApiException("wrong post id");
        }
        Users user=userRepository.findUsersByUserId(post.getUserId());
        if(user==null){
            throw new ApiException("wrong user id");
        }
        oldPost.setTitle(post.getTitle());
        oldPost.setContent(post.getContent());
        oldPost.setUserId(post.getUserId());
        oldPost.setPublicationDate(post.getPublicationDate());

        postsRepository.save(oldPost);
    }
    public void deletePost(Integer id){
        Posts post=postsRepository.findPostsByPostId(id);
        if(post==null){
            throw new ApiException("wrong post id");
        }
        postsRepository.delete(post);
    }
    public List<Posts> getAllByUserId(Integer id){
        Users user=userRepository.findUsersByUserId(id);
        if(user==null){
            throw new ApiException("user not found");
        }
        List<Posts> posts=postsRepository.findPostsByUserId(id);
        if(posts.isEmpty()){
            throw new ApiException("no posts from this user");
        }
        return posts;
    }
    public List<Posts> getAllByTitle(String title){
        List<Posts> posts=postsRepository.findPostsByTitle(title);
        if(posts.isEmpty()){
            throw new ApiException("title not found");
        }
        return posts;
    }
    public List<Posts> getPostsByBeforeDate(LocalDate date){
        List<Posts> postsDate=postsRepository.getPostsByPublicationDateIsBefore(date);
        return postsDate;
    }
}
