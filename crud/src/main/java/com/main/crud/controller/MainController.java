package com.main.crud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    // Mapeamento da rota principal ("/")
    @GetMapping("")
    public String showHomePage(){
        // Retorna o nome da view (página HTML) que será exibida
        return "index";
    }
}
