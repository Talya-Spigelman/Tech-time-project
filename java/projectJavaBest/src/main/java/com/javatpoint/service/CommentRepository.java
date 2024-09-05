package com.javatpoint.service;

import com.javatpoint.model.Comment;
import com.javatpoint.model.Post;
import org.springframework.stereotype.Component;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public interface CommentRepository  extends JpaRepository<Comment,Long> {
    Comment findAllById(Long ic);
    Comment findAllByPostComment(Post pcc);
    Comment findAllByDateUploadBefore(LocalDate duc);
}
