package com.codeup.codeupspringblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FirstController {
    @GetMapping("/hello")
    @ResponseBody
    public String returnHelloWorld(@RequestParam(defaultValue = "true") boolean uppercase){
        if (uppercase){
            return "HELLO WORLD";
        }else{
            return "Hello World";
        }
    }

    @GetMapping("/hello/{name}")
    @ResponseBody
    public String greetName(@PathVariable String name){
        return "Hello " + name;
    }

}
