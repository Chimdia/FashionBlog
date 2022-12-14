package com.chimy.chimyFashionBlog.response;

import com.chimy.chimyFashionBlog.model.Comment;
import com.chimy.chimyFashionBlog.model.Like;
import com.chimy.chimyFashionBlog.model.Post;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
public class LikeResponse {
    private  String message;
    private LocalDateTime timeStamp;
    private int totalLikes;
    private Like like;
}
