package com.chimy.chimyFashionBlog.controller;


import com.chimy.chimyFashionBlog.Service.UserService;
import com.chimy.chimyFashionBlog.dto.CommentDto;
import com.chimy.chimyFashionBlog.dto.LikeDto;
import com.chimy.chimyFashionBlog.dto.PostDto;
import com.chimy.chimyFashionBlog.dto.UserDto;
import com.chimy.chimyFashionBlog.response.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@Slf4j
@RequestMapping(value ="/api")
@AllArgsConstructor
public class UserController {

    private final UserService userService;


    @PostMapping(value = "/register")
    public ResponseEntity<RegisterResponse> reqister(@RequestBody UserDto userDto){
        log.info("Successfully Registered {} ", userDto.getEmail());
        return new ResponseEntity<>(userService.register(userDto), CREATED);

    }
    @PostMapping(value = "/create")
    public ResponseEntity<CreatePostResponse> create(@RequestBody PostDto postDto){
        log.info("Successfully Created A Post with Title: {} ",postDto.getTitle());
        return new ResponseEntity<>(userService.createPost(postDto) , CREATED);
    }
    @PostMapping(value = "/comment/{userId}/{postId}")
    public ResponseEntity<CommentResponse> comment(@PathVariable(value="userId") Integer userId, @PathVariable(value = "postId") Integer postId, @RequestBody CommentDto commentDto ){
        log.info("Successfully Commented : {} " , commentDto.getComment());
        return new ResponseEntity<>(userService.comment(userId , postId, commentDto) , CREATED);
    }
    @GetMapping(value = "/searchPost/{keyword}")
    public ResponseEntity<SearchPostResponse> searchPost(@PathVariable(value="keyword") String keyword){
        return new ResponseEntity<>(userService.searchPost(keyword), OK);
    }
    @PostMapping(value = "/like/{userId}/{postId}")
    public ResponseEntity<LikeResponse> like(@PathVariable(value = "userId") Integer userId, @PathVariable(value = "postId") Integer postId, @RequestBody LikeDto likeDto){
        log.info("Successfully Liked : {} ", likeDto.isLiked());
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/like").toUriString());
        return ResponseEntity.created(uri).body( userService.like(userId,postId,likeDto));
    }


    public ResponseEntity<SearchCommentResponse> searchComment(@PathVariable( value = "keyword") String keyword){
        return ResponseEntity.ok().body(userService.searchComment(keyword));
    }


}

