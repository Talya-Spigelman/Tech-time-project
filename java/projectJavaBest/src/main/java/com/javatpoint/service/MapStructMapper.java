package com.javatpoint.service;

import com.javatpoint.dto.PostDTO;
import com.javatpoint.dto.UserDTO;
import com.javatpoint.model.Comment;
import com.javatpoint.model.Content;
import com.javatpoint.model.Post;
import com.javatpoint.model.User;
import org.mapstruct.Mapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;


@Mapper(componentModel = "spring")
public interface MapStructMapper {

    List<PostDTO> postToDTO(List <Post> posts);

    default Post dtoToPost(PostDTO pd) throws IOException{

            Post post = new Post();

            post.setId(pd.getId());
            post.setCategory(pd.getCategory());
            post.setUserPost(pd.getUserPost());
            post.setDateUpload(pd.getDateUpload());
            post.setScore(pd.getScore());
            post.setTopic(pd.getTopic());
            post.setReadTime(pd.getReadTime());
            post.setComment(pd.getComment());

            List<Content> list = new ArrayList<>();
            List<String> strings = pd.getContents();
            for (int i =0; i< strings.size() ;i++){
                  list.add(new Content((long)0, strings.get(i), i, post));
            }
            post.setContent(list);
//            post.setImage(pd.getImagePath());
            return post;
//            StringBuilder builder = new StringBuilder();
//            List<Content> list = pd.getContent();

        }
    default PostDTO PostToDTO(Post p) throws IOException{

            PostDTO postDTO= new PostDTO();

            postDTO.setId(p.getId());
            postDTO.setCategory(p.getCategory());
            postDTO.setUserPost(p.getUserPost());
            postDTO.setDateUpload(p.getDateUpload());
            postDTO.setScore(p.getScore());
            postDTO.setTopic(p.getTopic());
            postDTO.setReadTime(p.getReadTime());
            postDTO.setComment(p.getComment());

            List<Content> list = p.getContent();
            List<String> list1 = new ArrayList<>();
            for (int i = 0; i < list.size(); i++){
                    list1.add(list.get(i).getText());
            }
            postDTO.setContents(list1);

//            Path filename = Paths.get( p.getImage());
//            byte[] byteInmage=Files.readAllBytes(filename);
//            postDTO.setImage(Base64.getEncoder().encode(byteInmage));
            return postDTO;
    }
        default User dtoToUser(UserDTO ud) throws IOException{

                User user = new User();

                user.setId(ud.getId());
                user.setMail(ud.getMail());
                user.setFirstName(ud.getFirstName());
                user.setLastName(ud.getLastName());
                user.setPassword(ud.getPassword());
                user.setStatus(ud.getStatus());
                user.setPostList(ud.getPostList());
                user.setCommentList(ud.getCommentList());

                user.setImage(ud.getImagePath());
                return user;
        }
        default UserDTO UserToDTO(User u) throws IOException{

                UserDTO userDTO= new UserDTO();

                userDTO.setId(u.getId());
                userDTO.setMail(u.getMail());
                userDTO.setFirstName(u.getFirstName());
                userDTO.setLastName(u.getLastName());
                userDTO.setPassword(u.getPassword());
                userDTO.setStatus(u.getStatus());
                userDTO.setPostList(u.getPostList());
                userDTO.setCommentList(u.getCommentList());

                Path filename = Paths.get( u.getImage());
                byte[] byteInmage=Files.readAllBytes(filename);
                userDTO.setImage(Base64.getEncoder().encode(byteInmage));
                return userDTO;
        }
}
