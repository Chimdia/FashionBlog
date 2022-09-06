package com.chimy.chimyFashionBlog.response;

import com.chimy.chimyFashionBlog.model.Comment;
import com.chimy.chimyFashionBlog.model.Post;

import java.time.LocalDateTime;

public class CommentResponse{
    private  String message;
    private LocalDateTime timeStamp;
    private Comment comment;
    private Post post;
}
