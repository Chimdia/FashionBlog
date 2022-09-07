package com.chimy.chimyFashionBlog.Service;

import com.chimy.chimyFashionBlog.dto.*;
import com.chimy.chimyFashionBlog.model.Post;
import com.chimy.chimyFashionBlog.response.*;
import org.springframework.stereotype.Service;


public interface UserService {

        RegisterResponse register(UserDto userDto);

        LoginResponse login(LoginDto loginDto);

        CreatePostResponse createPost(PostDto postDto);

        CommentResponse comment(int userId, int postId, CommentDto commentDto);

        LikeResponse like(int userId, int postId, LikeDto likeDto);


        SearchCommentResponse searchComment(String keyword);

        SearchPostResponse searchPost(String keyword);

        Post findPostById(int id);



}



