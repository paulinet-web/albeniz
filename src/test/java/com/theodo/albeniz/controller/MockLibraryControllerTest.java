package com.theodo.albeniz.controller;


import org.springframework.http.MediaType;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;


import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles(profiles = "mock")
public class MockLibraryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testgetMusic () throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/library/music").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(
                    "[{'id': 1, 'title':'You belong with me','author': 'Taylor Swift'}," + 
                    "{'id': 2, 'title':'Girls just want to have fun','author': 'Cyndi Lauper'}," + 
                    "{'id': 3, 'title':'Why not me?','author': 'The Judds'}]"));
    }

    @Test
    public void testgetMusicQuery () throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/library/music?query=WHY").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(
                    "[{'id': 3, 'title':'Why not me?','author': 'The Judds'}]"));
    }


    @Test
    public void testgetOneMusic () throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/library/music/2").contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().json("{'id': 2, 'title':'Girls just want to have fun','author': 'Cyndi Lauper'}"));

    }

}

