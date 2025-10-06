package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

@RestController
public class HelloController {

    @GetMapping("/greeting")
    public String greeting(@RequestParam(value = "name", required = false) String name) {
        String safeName = name != null ? HtmlUtils.htmlEscape(name) : "World";
        return "Hello " + safeName + "!";
    }
}
