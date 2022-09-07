package com.chimy.chimyFashionBlog.response;

import com.chimy.chimyFashionBlog.model.Comment;
import com.chimy.chimyFashionBlog.model.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponse{
    private  String message;
    private LocalDateTime timeStamp;
    private Comment comment;
    private Post post;
}
