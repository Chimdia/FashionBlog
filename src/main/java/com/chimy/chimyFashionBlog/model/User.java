package com.chimy.chimyFashionBlog.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @Column(unique = true)
    private String email;
    private String role;
    private String password;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @JsonManagedReference
    @OneToMany(mappedBy = "user")
    private List<Post> postLike = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "user")
    private  List<Comment> commentList = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "user")
    private  List<Like> listList = new ArrayList<>();
}
