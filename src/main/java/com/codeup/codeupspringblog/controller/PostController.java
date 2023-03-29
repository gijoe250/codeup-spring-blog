package com.codeup.codeupspringblog.controller;

import com.codeup.codeupspringblog.Dao.PostRepository;
import com.codeup.codeupspringblog.model.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class PostController {
    private final PostRepository postDao;

    public PostController(PostRepository postDao) {
        this.postDao = postDao;
    }
    @RequestMapping(path = "/posts", method = RequestMethod.GET)
    public String indexPage(Model model){
        model.addAttribute("posts", postDao.findAll());
        return "posts/index";
    }

    @RequestMapping(path = "/posts/{id}", method = RequestMethod.GET)
    public String idPage(@PathVariable int id, Model model){
//        Alexia = model.addAttribute("post", postDao.findById(id).get());
//        Mine = model.addAttribute("post", postDao.findPostById(id));
        Optional<Post> optionalPost = postDao.findById((id));
        if (optionalPost.isPresent()){
            model.addAttribute("post", optionalPost.get());
            return "posts/show";
        } else {
            return "redirect:/posts";
        }

    }

    @RequestMapping(path = "/posts/create", method = RequestMethod.GET)
    public String createPageGet(){
        return "posts/create";
    }

    @RequestMapping(path = "/posts/create", method = RequestMethod.POST)
    public String createPagePost(@RequestParam(name = "title") String title, @RequestParam(name = "description") String description, Model model){
        Post post = new Post(title, description);
        postDao.save(post);
        return "redirect:/posts";
    }
}
