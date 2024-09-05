package com.javatpoint.model;

//package com.javatpoint.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
public class Comment {
    @Id
    @GeneratedValue
    private Long id;
    private String topic;
    private String content;
    private LocalDate dateUpload;
    private int score;


    @ManyToOne //there are many comment for one post
   @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public Post postComment;


    @ManyToOne //there are many comment for one user
    private User userComment;

    public Comment(Long id, String topic, String content, LocalDate dateUpload, int score, Post postComment, User userComment) {
        this.id = id;
        this.topic = topic;
        this.content = content;
        this.dateUpload = dateUpload;
        this.score = score;
            this.postComment = postComment;
        this.userComment = userComment;
    }

    public Comment() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDate getDateUpload() {
        return dateUpload;
    }

    public void setDateUpload(LocalDate dateUpload) {
        this.dateUpload = dateUpload;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Post getPostComment() {
        return postComment;
    }

    public void setPostComment(Post post) {
        this.postComment = post;
    }

    public User getUserComment() {
        return userComment;
    }

    public void setUserComment(User user) {
        this.userComment = user;
    }

}
