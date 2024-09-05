package com.javatpoint.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.javatpoint.model.Comment;
import com.javatpoint.model.Post;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String mail;
    private String firstName;
    private String lastName;
    private String password;
    private String status;
    private String image;

    @OneToMany(mappedBy = "userPost") //user can upload many posts
    @JsonIgnore
    private List<Post> postList;


    @OneToMany(mappedBy = "userComment") // user can upload many comment
    @JsonIgnore
    private List<Comment> commentList;

    public User(Long id, String mail, String firstName, String lastName, String password, String status, String image, List<Post> postList, List<Comment> commentList) {
        this.id = id;
        this.mail = mail;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.status = status;
        this.image = image;
       // this.postList = postList;
    }

    public User() {
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Post> getPostList() {
        return postList;
    }

    public void setPostList(List<Post> postList) {
        this.postList = postList;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

}
