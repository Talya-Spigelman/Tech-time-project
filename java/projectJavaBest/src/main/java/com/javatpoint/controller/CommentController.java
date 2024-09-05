package com.javatpoint.controller;

import com.javatpoint.model.Category;
import com.javatpoint.model.Comment;
import com.javatpoint.model.Post;
import com.javatpoint.model.User;
import com.javatpoint.service.CommentRepository;
import com.javatpoint.service.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/comment")
public class CommentController {

    private CommentRepository commentRepository;

    @Autowired
    public CommentController(CommentRepository commentRepository) {
        this.commentRepository= commentRepository;
    }


    //פונקציה שמראה את כל התגובות
    @GetMapping("/get")
    public ResponseEntity<List<Comment>> getComments(){
        try {
            List<Comment> comments= new ArrayList<>();
            commentRepository.findAll().forEach(e->comments.add(e));
            return new ResponseEntity<>(comments, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //פונקציה שמראה תגובה לפי ID שנותנים לו
    @GetMapping("/get/{id}")
    public ResponseEntity<Comment> getCommentById(@PathVariable long id){
        Comment cm = commentRepository.findById(id).orElse(null);
        if(cm != null){
            return new ResponseEntity<>(cm,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //פונקציה שיוצרת תגובה
    @PostMapping("/create")
    public ResponseEntity<Comment> createComment(@RequestBody Comment c){
        try{
            Comment newComment=commentRepository.save(c);
            return new ResponseEntity<>(newComment,HttpStatus.CREATED);

        }
        catch (Exception e){
            System.out.println(e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //פונקציות מיון
    //לפי ID
    @GetMapping ("/commentById")
    public ResponseEntity commentById(@RequestBody Comment c){
        Comment c1=commentRepository.findAllById(c.getId());
        return new ResponseEntity<>(c1, HttpStatus.OK);
    }
    //לפי פובט
    @GetMapping ("/commentByPost")
    public ResponseEntity commentByPost(@RequestBody Comment c){
        Comment c1=commentRepository.findAllByPostComment(c.getPostComment());
        return new ResponseEntity<>(c1,HttpStatus.OK);
    }
    //לפי תאריך היצירה
    @GetMapping("/commentByDateUpload")
    public ResponseEntity commentByDateUpload(@RequestBody Comment c){
        Comment c1= commentRepository.findAllByDateUploadBefore(c.getDateUpload());
        return new ResponseEntity<>(c1,HttpStatus.OK);
    }

}
