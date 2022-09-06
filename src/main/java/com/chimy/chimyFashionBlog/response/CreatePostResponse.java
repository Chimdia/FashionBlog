package com.chimy.chimyFashionBlog.response;

import com.chimy.chimyFashionBlog.model.Comment;
import com.chimy.chimyFashionBlog.model.Post;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
public class CreatePostResponse {
    private  String message;
    private LocalDateTime timeStamp;
    private Post post;
}
