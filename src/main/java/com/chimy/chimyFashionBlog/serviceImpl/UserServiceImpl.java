package com.chimy.chimyFashionBlog.serviceImpl;

import com.chimy.chimyFashionBlog.Service.UserService;
import com.chimy.chimyFashionBlog.dto.*;
import com.chimy.chimyFashionBlog.exception.PostAlreadyLikedException;
import com.chimy.chimyFashionBlog.exception.PostNotFoundException;
import com.chimy.chimyFashionBlog.exception.UserNotFoundException;
import com.chimy.chimyFashionBlog.model.Comment;
import com.chimy.chimyFashionBlog.model.Like;
import com.chimy.chimyFashionBlog.model.Post;
import com.chimy.chimyFashionBlog.model.User;
import com.chimy.chimyFashionBlog.repository.CommentRepository;
import com.chimy.chimyFashionBlog.repository.LikeRepository;
import com.chimy.chimyFashionBlog.repository.PostRepository;
import com.chimy.chimyFashionBlog.repository.UserRepository;
import com.chimy.chimyFashionBlog.response.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final LikeRepository likeRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private static final Pattern NONLATIN = Pattern.compile("[^\\w-]");
    private static final Pattern WHITESPACE = Pattern.compile("[\\s]");

    @Override
    public RegisterResponse register(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setRole(userDto.getRole());
        userRepository.save(user);
        return  new RegisterResponse("success" , LocalDateTime.now(), user);
    }

    @Override
    public LoginResponse login(LoginDto loginDto) {
        User guest = findUserByEmail(loginDto.getEmail());
        LoginResponse loginResponse = null;
        if(guest != null){
            if(guest.getPassword().equals(loginDto.getPassword())){
                loginResponse =new LoginResponse("success", LocalDateTime.now());
            }else{
                loginResponse = new LoginResponse("password MisMatch", LocalDateTime.now());
            }
        }

        return loginResponse;
    }

    private User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email).orElseThrow(()-> new UserNotFoundException("User with email: " + email + "cannot be Found"));
    }


    @Override
    public CreatePostResponse createPost(PostDto postDto) {
        Post post = new Post();
        User user = findUserById(postDto.getUserId());
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setSlug(makeSlug(postDto.getTitle()));
        post.setImage(postDto.getImage());
        post.setUser(user);
        postRepository.save(post);
        return new CreatePostResponse("success", LocalDateTime.now(), post);

    }

    private String makeSlug(String input) {
        String nowhitespace = WHITESPACE.matcher(input).replaceAll("-");
        String normalized = Normalizer.normalize(nowhitespace, Normalizer.Form.NFD);
        String slug = NONLATIN.matcher(normalized).replaceAll("");
        return slug.toLowerCase(Locale.ENGLISH);
    }

    private User findUserById(int id) {
       return userRepository.findById(id).orElseThrow(()->  new UserNotFoundException("user with ID: " + id + "NotFound") );
    }

    @Override
    public CommentResponse comment(int userId, int postId, CommentDto commentDto) {
        User user = findUserById(userId);
        Post post = findPostById(postId);
        Comment comment = new Comment();
       comment.setComment(commentDto.getComment());
        comment.setUser(user);
        comment.setPost(post);
        commentRepository.save(comment);
        return new CommentResponse("success", LocalDateTime.now(), comment,post);

    }

    @Override
    public LikeResponse like(int userId, int postId, LikeDto likeDto) {
        Like like = new Like();
        User user = findUserById(userId);
        Post post = findPostById(postId);
        LikeResponse likeResponse =null;
        Like duplicateLike = likeRepository.findLikeByUserIdAndPostId(userId, postId);
        if(duplicateLike==null){
            like.setLiked(likeDto.isLiked());
            like.setUser(user);
            like.setPost(post);
            likeRepository.save(like);
            List<Like> likeList = likeRepository.likeList(postId);
            likeResponse = new LikeResponse("success", LocalDateTime.now(), likeList.size(), like);

        }else {
            likeRepository.delete(duplicateLike);
            throw new PostAlreadyLikedException("Already liked");
        }
        return likeResponse;
    }

    @Override
    public SearchCommentResponse searchComment(String keyword) {
        List<Comment> commentList = commentRepository.findByCommentContaining(keyword);
        return new SearchCommentResponse("success" , LocalDateTime.now() , commentList);
    }

    @Override
    public SearchPostResponse searchPost(String keyword) {
        List<Post> postList = postRepository.findByTitleContainingIgnoreCase(keyword);
        return new SearchPostResponse("success", LocalDateTime.now(), postList);
    }

    @Override
    public Post findPostById(int id) {
        return postRepository.findById(id).orElseThrow(()-> new PostNotFoundException("Post " + " Not Found"));
    }
}
