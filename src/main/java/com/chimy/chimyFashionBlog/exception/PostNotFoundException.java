package com.chimy.chimyFashionBlog.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostNotFoundException extends RuntimeException {
    private String message;

}
