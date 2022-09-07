package com.chimy.chimyFashionBlog.repository;

import com.chimy.chimyFashionBlog.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LikeRepository extends JpaRepository<Like, Integer> {

    @Query(value = "SELECT * FROM Likes WHERE userId = ?1 AND postId = ?2", nativeQuery = true)
    Like findLikeByUserIdAndPostId(int userId, int postId);

    @Query(value ="SELECT * FROM Likes WHERE postId = ?1", nativeQuery = true)
    List<Like> likeList(int postId);

}
