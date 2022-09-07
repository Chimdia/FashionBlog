package com.chimy.chimyFashionBlog.repository;

import com.chimy.chimyFashionBlog.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findByCommentContaining(String keyword);
}
