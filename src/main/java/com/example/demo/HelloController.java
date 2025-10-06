package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/greeting")
    public String greeting(@RequestParam(value = "name", required = false) String name) {
        return name != null ? "Hello " + name + "!" : "Hello World!";
    }
}
