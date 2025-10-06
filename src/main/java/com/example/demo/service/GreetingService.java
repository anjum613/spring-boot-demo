package com.example.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class GreetingService {

    /**
     * Returns "Hello, {name}!" if name is non-null and non-blank;
     * otherwise returns "Hello, World!".
     */
    public String greet(String name) {
        String effective = StringUtils.hasText(name) ? name : "World";
        return "Hello, " + effective + "!";
    }
}
