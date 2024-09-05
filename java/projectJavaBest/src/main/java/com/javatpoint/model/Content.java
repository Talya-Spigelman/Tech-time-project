package com.javatpoint.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;


@Entity
public class Content {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;
    private int numRow;

    @ManyToOne // there are many rows of content on every post
   @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Post post;

    public Content(Long id, String text, int numRow, Post post) {
        this.id = id;
        this.text = text;
        this.numRow = numRow;
        this.post = post;
    }

    public Content() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getNumRow() {
        return numRow;
    }

    public void setNumRow(int numRow) {
        this.numRow = numRow;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}

