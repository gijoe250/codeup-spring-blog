package com.codeup.codeupspringblog.controller;

import com.codeup.codeupspringblog.Dao.PostRepository;
import com.codeup.codeupspringblog.Dao.UserRepository;
import com.codeup.codeupspringblog.model.Ad;
import com.codeup.codeupspringblog.model.Post;
import com.codeup.codeupspringblog.model.User;
import com.codeup.codeupspringblog.services.EmailService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class PostController {
    private final PostRepository postDao;
    private final UserRepository usersDao;
    private final EmailService emailService;

    public PostController(PostRepository postDao, UserRepository usersDao, EmailService emailService) {
        this.postDao = postDao;
        this.usersDao = usersDao;
        this.emailService = emailService;
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
    public String createPageGet(Model model){
        model.addAttribute("post", new Post());
        return "posts/create";
    }

    @RequestMapping(path = "/posts/create", method = RequestMethod.POST)
    public String createPagePost(@ModelAttribute Post post){
        User user = usersDao.findById(1);
        post.setUser(user);
        postDao.save(post);
        emailService.prepareAndSend( post,"test", "this is the body of the email?");
        return "redirect:/posts";
    }

    @GetMapping("/posts/{id}/edit")
    public String editPostGet(Model model, @PathVariable int id){
        Optional<Post> optionalPost = postDao.findById((id));
        if (optionalPost.isPresent()){
            model.addAttribute("post", optionalPost.get());
            return "posts/edit";
        } else {
            System.out.println("id does not exist");
            return "redirect:/posts";
        }
    }

    @PostMapping("/posts/{id}/edit")
    public String editPostPost(@ModelAttribute Post post){
        postDao.save(post);
        return "redirect:/posts";
    }
}
