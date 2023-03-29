package com.codeup.codeupspringblog.controller;

import com.codeup.codeupspringblog.Dao.AdRepository;
import com.codeup.codeupspringblog.Dao.UserRepository;
import com.codeup.codeupspringblog.model.Ad;
import com.codeup.codeupspringblog.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {
    private final UserRepository usersDao;

    public UserController(UserRepository usersDao) {
        this.usersDao = usersDao;
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam(name="username") String username, @RequestParam(name="email") String email, @RequestParam(name="password") String password){
        User user = new User();
        return "redirect:/ads";
    }

    @GetMapping("/user/{id}/ads")
    public String userAds(@PathVariable long id, Model model){
        System.out.println(id);
        User user = usersDao.findById(id);
        System.out.println(user.getId() + " user ads");
        List<Ad> userAds = user.getAds();
        model.addAttribute("userAds", userAds);
        return "/ads/userAds";
    }
}
