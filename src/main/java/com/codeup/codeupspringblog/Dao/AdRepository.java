package com.codeup.codeupspringblog.Dao;

import com.codeup.codeupspringblog.model.Ad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdRepository extends JpaRepository<Ad, Long> {
    Ad findById(long id);
}
