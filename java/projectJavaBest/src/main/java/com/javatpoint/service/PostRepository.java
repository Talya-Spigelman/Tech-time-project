package com.javatpoint.service;

import com.javatpoint.model.Category;
import com.javatpoint.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
@Component
public interface PostRepository  extends JpaRepository<Post,Long> {
        Post findAllById(Long ip);
        Post findAllByCategory_Id(Category cp);
        Post findAllByDateUploadBefore(LocalDate dup);
        Post findAllByScore(int sp);
}
