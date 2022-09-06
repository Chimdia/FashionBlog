package com.chimy.chimyFashionBlog.response;

import com.chimy.chimyFashionBlog.model.Post;

import java.time.LocalDateTime;
import java.util.List;

public class SearchPostResponse {
    private  String message;
    private LocalDateTime timeStamp;
    private List<Post> post;
}
