package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
    
    @GetMapping("/")
    @ResponseBody
    public String home() {
        return "<h1>Welcome to Product API</h1>" +
               "<p>Try these endpoints:</p>" +
               "<ul>" +
               "<li>GET /api/v1/products - View all products</li>" +
               "<li>POST /api/v1/product - Create a product</li>" +
               "</ul>";
    }
}
