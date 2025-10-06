package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = HelloController.class)
class HelloControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGreetingWithName() throws Exception {
        mockMvc.perform(get("/greeting?name=John"))
               .andExpect(status().isOk())
               .andExpect(content().string("Hello John!"));
    }

    @Test
    void testGreetingWithoutName() throws Exception {
        mockMvc.perform(get("/greeting"))
               .andExpect(status().isOk())
               .andExpect(content().string("Hello World!"));
    }
}
