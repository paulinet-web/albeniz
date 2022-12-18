package com.theodo.albeniz.controller;


import org.springframework.http.MediaType;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;


import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class LibraryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getMusic () throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/library/music").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(
                    "[{'title':'You belong with me','author': 'Taylor Swift'}," + 
                    "{'title':'Girls just want to have fun','author': 'Cyndi Lauper'}," + 
                    "{'title':'Why not me?','author': 'The Judds'}]"));

}
  
    
}
