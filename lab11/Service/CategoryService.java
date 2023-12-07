package com.example.lab11.Service;

import com.example.lab11.Api.ApiException;
import com.example.lab11.Model.Categories;
import com.example.lab11.Model.Posts;
import com.example.lab11.Repository.CategoriesRepository;
import com.example.lab11.Repository.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoriesRepository categoriesRepository;
    private final PostsRepository postsRepository;
    public List<Categories> getAllCategories(){
        return categoriesRepository.findAll();
    }
    public void addCategory(Categories category){
        Posts post=postsRepository.findPostsByPostId(category.getPostId());
        if(post==null){
            throw new ApiException("wrong post id");
        }
        categoriesRepository.save(category);
    }
    public void updateCategory(Integer id,Categories category){
        Categories oldCategory=categoriesRepository.findCategoriesByCategoryId(id);
        if(oldCategory==null){
            throw new ApiException("category id not found");
        }
        Posts post=postsRepository.findPostsByPostId(category.getPostId());
        if(post==null){
            throw new ApiException("wrong post id");
        }
        oldCategory.setCategoryName(category.getCategoryName());
        oldCategory.setPostId(category.getPostId());

        categoriesRepository.save(oldCategory);
    }
    public void deleteCategory(Integer id){
        Categories category=categoriesRepository.findCategoriesByCategoryId(id);
        if(category==null){
            throw new ApiException("category id not found");
        }
        categoriesRepository.delete(category);
    }
    //7
    public List<Integer> findPostsIdByCat(String categoryName){
        List<Integer>findPostsIdByCat=categoriesRepository.findPostsIdByCat(categoryName);
        if(findPostsIdByCat.isEmpty()){
            throw new ApiException("no posts in this category id");
        }
        return findPostsIdByCat;
    }
}
