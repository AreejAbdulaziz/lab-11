package com.example.lab11.Repository;

import com.example.lab11.Model.Categories;
import com.example.lab11.Model.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriesRepository extends JpaRepository<Categories,Integer> {
    Categories findCategoriesByCategoryId(Integer id);

    @Query("select c.postId from Categories c where c.categoryName=?1")
    List<Integer> findPostsIdByCat(String category);
}
