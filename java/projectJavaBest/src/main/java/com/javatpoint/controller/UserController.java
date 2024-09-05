package com.javatpoint.controller;

import com.javatpoint.dto.PostDTO;
import com.javatpoint.dto.UserDTO;
import com.javatpoint.model.Post;
import com.javatpoint.model.User;
import com.javatpoint.service.MapStructMapper;
import com.javatpoint.service.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/user")
public class UserController {

    private UserRepository userRepository;
    private MapStructMapper mapper;

    @Autowired
    public UserController(UserRepository userRepository, MapStructMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @GetMapping("/get")
    public ResponseEntity<List<User>> getUsers(){
        try {
            List<User> users= new ArrayList<>();
            userRepository.findAll().forEach(e->users.add(e));
            return new ResponseEntity<>(users,HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<User> getUserById(@PathVariable long id){
        User u = userRepository.findById(id).orElse(null);
        if(u != null){
            return new ResponseEntity<>(u, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User u){
        try{
            User newUser=userRepository.save(u);
            return new ResponseEntity<>(newUser,HttpStatus.CREATED);

        }
        catch (Exception e){
            System.out.println(e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    ////////////////////////////////////////////


    @PostMapping("/update")
    public ResponseEntity<User> updateUser(@RequestBody User u1){
        User u = userRepository.findById(u1.getId()).orElse(null);

        try{
            u.setImage(u1.getImage());
            u.setFirstName(u1.getFirstName());
            u.setLastName(u1.getLastName());
            u.setStatus(u1.getStatus());
            return new ResponseEntity<>(u,HttpStatus.CREATED);
        }
        catch (Exception e){
            System.out.println(e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping ("/update/{id}")
    public ResponseEntity<User> updateUser2(@PathVariable long id ,@RequestBody User u1){
        User u = userRepository.findById(id).orElse(null);
        if(u != null){
            u.setImage(u1.getImage());
            u.setFirstName(u1.getFirstName());
            u.setLastName(u1.getLastName());
            u.setStatus(u1.getStatus());
            userRepository.save(u);
            return new ResponseEntity<>(u,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/logIn")
    public ResponseEntity userLogIn(@RequestBody User u){
        User u1=userRepository.findByMail(u.getMail());
        if(u1 != null && u1.getPassword().equals(u.getPassword()))
            return new ResponseEntity(u1,HttpStatus.OK);
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }


//    לא בדוק

    @PostMapping("/uploadUser")
    public ResponseEntity<User> uploadPostWithImage(@RequestPart("image") MultipartFile file,
                                                    @RequestPart("user") UserDTO u){
        try {
            User newUser= userRepository.save(mapper.dtoToUser(u));
            return new ResponseEntity(newUser,HttpStatus.CREATED);
        }
        catch (Exception e){
            System.out.println(e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping ("/signUpByMail")
    public ResponseEntity signUpByMail(@RequestBody User u){
        User u1=userRepository.findUserByMail(u.getMail());
        if(u1.getPassword().equals(u.getPassword()))
            return new ResponseEntity(u1,HttpStatus.OK);
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PostMapping ("/signUpByPassword")
    public ResponseEntity signUpByPassword(@RequestBody User u){
        User u1=userRepository.findUserByPassword(u.getPassword());
        if(u1.getMail().equals(u.getMail()))
            return new ResponseEntity(u1,HttpStatus.OK);
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping ("/userById")
    public ResponseEntity userById(@RequestBody User u){
        User u1=userRepository.findAllById(u.getId());
        return new ResponseEntity<>(u1,HttpStatus.OK);
    }
    @GetMapping ("/userByPost")
    public ResponseEntity userByPost(@RequestBody User u){
        User u1=userRepository.findAllByPostListIn(u.getPostList());
        return new ResponseEntity<>(u1,HttpStatus.OK);
    }
    @GetMapping ("/userByComment")
    public ResponseEntity userByComment(@RequestBody User u){
        User u1=userRepository.findAllByCommentListIn(u.getCommentList());
        return new ResponseEntity<>(u1,HttpStatus.OK);
    }


    @GetMapping("/getdto/{id}")
    public ResponseEntity<UserDTO> getDTO(@PathVariable long id) throws IOException {
        User u = userRepository.findById(id).orElse(null);
        if(u != null){
            return new ResponseEntity<>(mapper.UserToDTO(u), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    }