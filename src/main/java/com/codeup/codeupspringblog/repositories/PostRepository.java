package com.codeup.codeupspringblog.repositories;

import com.codeup.codeupspringblog.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {
    Post findPostById(int id);
}
