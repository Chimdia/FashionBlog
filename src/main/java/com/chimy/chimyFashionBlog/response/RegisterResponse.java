package com.chimy.chimyFashionBlog.response;

import com.chimy.chimyFashionBlog.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
public class RegisterResponse {
    private  String message;
    private LocalDateTime timeStamp;
    private User user;

}
