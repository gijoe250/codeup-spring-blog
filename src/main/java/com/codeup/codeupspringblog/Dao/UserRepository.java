package com.codeup.codeupspringblog.Dao;

import com.codeup.codeupspringblog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findById(long id);
}
