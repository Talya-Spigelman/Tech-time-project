package com.javatpoint.controller;
import com.javatpoint.dto.PostDTO;
import com.javatpoint.dto.UserDTO;
import com.javatpoint.model.Comment;
import com.javatpoint.model.Post;
import com.javatpoint.service.MapStructMapper;
import com.javatpoint.service.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;



@CrossOrigin
@RestController
@RequestMapping("/api/post")
public class PostController {
    
    //Any function that will be here can also receive an address
    private PostRepository postRepository;
    private MapStructMapper mapper;
    private static String UPLOAD_DIRECTORY = System.getProperty("user.dir")+"\\images\\";

    @Autowired
    public PostController(PostRepository postRepository, MapStructMapper mapper) {
        this.postRepository = postRepository;
        this.mapper = mapper;
    }
    //פונקציה שמראה את כל הפוסטים
    @GetMapping("/get")
    public ResponseEntity<List< PostDTO>> getPosts(){
        try {
            List<Post> posts= new ArrayList<>();
            postRepository.findAll().forEach(e->posts.add(e));
            return new ResponseEntity<>(mapper.postToDTO(posts),HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //פוננקציה שמראה פוסט לפי ID
    @GetMapping("/get/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable long id){
      Post p = postRepository.findById(id).orElse(null);
      if(p != null){
          return new ResponseEntity(p,HttpStatus.OK);
      }
      else{
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    }

    //פונקציה שיוצרת פוסט ללא מערך של מחרוזות בטבלת CONTENT
    @PostMapping("/create")
    public ResponseEntity<Post> createPost(@RequestBody Post p){
        try{
            Post newPost=postRepository.save(p);
            return new ResponseEntity<>(newPost,HttpStatus.CREATED);
        }
        catch (Exception e){
            System.out.println(e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // פונקציה שיוצרת פוסט עם מערך של מחרוזות מטבלת ה- CONTENT
    @PostMapping("/create2")
    public ResponseEntity<Post> create2(@RequestBody PostDTO p) throws IOException {
        try{
            Post newPost=postRepository.save(mapper.dtoToPost(p));
            return new ResponseEntity<>(newPost,HttpStatus.CREATED);
        }
        catch (Exception e){
            System.out.println(e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR)  ;
        }
    }

    //פונקציה שמראה מערך של מחרוזות מטבלת CONTENT
    @GetMapping("/getdto/{id}")
    public ResponseEntity<PostDTO> getDTO(@PathVariable long id) throws IOException {
      Post p = postRepository.findById(id).orElse(null);
      if(p != null){
          return new ResponseEntity<>(mapper.PostToDTO(p), HttpStatus.OK);
      }
      else{
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    }

    //פונקציות מיון
    //מציג את כל הפוסטים לפי ID
    @GetMapping ("/postById")
    public ResponseEntity postById(@RequestBody Post p){
        Post p1=postRepository.findAllById(p.getId());
        return new ResponseEntity<>(p1, HttpStatus.OK);
    }

    //מציג את כל הפוסטים לפי קטגוריות
    @GetMapping ("/postByCategory")
    public ResponseEntity postByCategory(@RequestBody Post p){
        Post p1=postRepository.findAllByCategory_Id(p.getCategory());
        return new ResponseEntity<>(p1,HttpStatus.OK);
    }

    //מציג את כל הפוסטים לפי תאריך יצירה
    @GetMapping ("/postByDateUpload")
    public ResponseEntity postByDateUpload(@RequestBody Post p){
        Post p1=postRepository.findAllByDateUploadBefore(p.getDateUpload());
        return new ResponseEntity<>(p1,HttpStatus.OK);
    }

    //מציג את כל הפוסטים לפי ניקוד מהגבוה לנמוך
    @GetMapping ("/postByScore")
    public ResponseEntity postByScore(@RequestBody Post p){
        Post p1=postRepository.findAllByScore(p.getScore());
        return new ResponseEntity<>(p1,HttpStatus.OK);
    }

}

