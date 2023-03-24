package com.codeup.codeupspringblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RollDiceController {

    @GetMapping("/roll-dice")
    public String here(){
        return "roll-dice";
    }

    @PostMapping("/roll-dice")
    public String guessHTML(@RequestParam(name = "number") int n){
        return "redirect:/roll-dice/" + n;
    }

    @GetMapping("/roll-dice/{n}")
    public String guess(@PathVariable int n, Model model){
        int max = 6;
        int min = 1;
        int answer = (int)Math.floor(Math.random() * (max - min + 1) + min);
        String correctString = "incorrect???";
        if (answer == n){
            correctString = "correct!";
        }
        String string = "Your guess is " + n +
                ", the answer is " + answer +
                ", your guess was " + correctString;
        System.out.println(string);
        model.addAttribute("string", string);
        //
        return "guess";
    }
}
