package com.example.StefFood.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class Hello {

    @RequestMapping("/")
    @ResponseBody
    public String hello() {
        return "Bem vindo a StefFood";
    }
}
