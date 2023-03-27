package com.codeup.codeupspringblog.controller;

import com.codeup.codeupspringblog.model.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {
    @RequestMapping(path = "/posts", method = RequestMethod.GET)
    public String indexPage(Model model){
        Post post2 = new Post(2, "Test2", "This is the test2 post");
        Post post3 = new Post(3, "Test3", "This is the test3 post");

        List<Post> posts = new ArrayList<>() {
            {
                add(post2);
                add(post3);
            }
        };
        model.addAttribute("posts", posts);

        return "posts/index";
    }

    @RequestMapping(path = "/posts/{id}", method = RequestMethod.GET)
    public String idPage(@PathVariable int id, Model model){
        Post post = new Post(id, "Test", "This is the test post");
        model.addAttribute("post", post);
        return "posts/show";
    }

    @RequestMapping(path = "/posts/create", method = RequestMethod.GET)
    @ResponseBody
    public String createPageGet(){
        return "<form action=\"/posts/create\" method=\"POST\">\n" +
                "            <input type=\"submit\" class=\"btn btn-primary btn-block\" value=\"Log In\">\n" +
                "</form>" +
                "view the form for creating a post";
    }

    @RequestMapping(path = "/posts/create", method = RequestMethod.POST)
    @ResponseBody
    public String createPagePost(){
        return "create a new post";
    }
}
