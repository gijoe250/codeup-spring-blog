package com.codeup.codeupspringblog.controller;

import com.codeup.codeupspringblog.repositories.UserRepository;
import com.codeup.codeupspringblog.model.Ad;
import com.codeup.codeupspringblog.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {
    private final UserRepository usersDao;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserRepository userDao, PasswordEncoder passwordEncoder) {
        this.usersDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/sign-up")
    public String showSignupForm(Model model){
        model.addAttribute("user", new User());
        return "users/sign-up";
    }

    @PostMapping("/sign-up")
    public String saveUser(@ModelAttribute User user){
        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        usersDao.save(user);
        return "redirect:/login";
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
