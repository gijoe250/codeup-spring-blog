package com.codeup.codeupspringblog.controller;

import com.codeup.codeupspringblog.Dao.AdRepository;
import com.codeup.codeupspringblog.Dao.UserRepository;
import com.codeup.codeupspringblog.model.Ad;
import com.codeup.codeupspringblog.model.Post;
import com.codeup.codeupspringblog.model.User;
import com.codeup.codeupspringblog.services.EmailService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class AdController {
    private final AdRepository adsDao;
    private final UserRepository usersDao;

    public AdController(AdRepository adsDao, UserRepository usersDao){
        this.usersDao = usersDao;
        this.adsDao = adsDao;
    }

    @GetMapping("/ads")
    public String allAds(Model model){
        model.addAttribute("ads", adsDao.findAll());
        return "/ads/index";
    }

    @GetMapping("/ads/create")
    public String postAdForm(){
        return "ads/create";
    }

    @PostMapping("/ads/create")
    public String postAd(@RequestParam(name="title") String title, @RequestParam(name="description") String description){
        User user = usersDao.findById(1);
        Ad ad = new Ad(title, description, user);
        adsDao.save(ad);
        return "redirect:/ads";
    }

    @GetMapping("/ads/{id}")
    public String showOneAd(@PathVariable long id, Model model){
        Ad ad = adsDao.findById(id);
        model.addAttribute(ad == null? new Ad("not found", "Could not find that ad") : ad);
        return "/ads/show";
    }
}
