package com.chimy.chimyFashionBlog.dto;

import lombok.Data;

@Data
public class PostDto {
    private String title;
    private String description;
    private String image;
    private int userId;
}
