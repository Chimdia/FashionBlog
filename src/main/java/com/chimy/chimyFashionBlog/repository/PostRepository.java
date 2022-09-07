package com.chimy.chimyFashionBlog.repository;

import com.chimy.chimyFashionBlog.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    List<Post> findByTitleContainingIgnoreCase(String keyword);
}
