package com.javatpoint.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Post {
    @Id
    @GeneratedValue
    private Long id;
    private String topic;
    private String readTime;
    private String image;
    private LocalDate dateUpload;
    private int score;

    @ManyToOne //there are many posts on one category
    private Category category;

    @OneToMany(mappedBy = "postComment") //post has many comment
    private List<Comment> comment;


    @OneToMany (mappedBy = "post", cascade = CascadeType.ALL) //post has many rows
    private List<Content> contents;

    @ManyToOne  //there are many posts that one user upload
    private User userPost;

    public Post(Long id, String topic, String readTime, String image, LocalDate dateUpload, int score, Category category, List<Comment> comment, List<Content> contents, User userPost) {
        this.id = id;
        this.topic = topic;
        this.readTime = readTime;
        this.image = image;
        this.dateUpload = dateUpload;
        this.score = score;
        this.category = category;
        this.comment = comment;
        this.contents = contents;
        this.userPost = userPost;

    }

    public Post() {
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

    public String getReadTime() {
        return readTime;
    }

    public void setReadTime(String readTime) {
        this.readTime = readTime;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public User getUserPost() {
        return userPost;
    }

    public void setUserPost(User user) {
        this.userPost = user;
    }

    public List<Content> getContent() {
        return contents;
    }

    public void setContent(List<Content> contentList) {
        this.contents = contentList;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category newCategory) {
        this.category = newCategory;
    }

    public List<Comment> getComment() {
        return comment;
    }

    public void setComment(List<Comment> comment) {
        this.comment = comment;
    }

}

