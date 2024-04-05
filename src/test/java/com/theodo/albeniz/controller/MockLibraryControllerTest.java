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
    MockMvc mockMvc;

    private final String CONTROLLER_PATH = "/library/music";


    @Test
    public void testGetMusic () throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(CONTROLLER_PATH).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(
                  "[{'id': 1, 'title':'You belong with me','author':'Taylor Swift'},"+
                    "{'id': 2, 'title':'Girls just want to have fun','author': 'Cyndi Lauper'}]"));

    }

    @Test
    public void testGetMusicWithQuery () throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(CONTROLLER_PATH + "?query=WhY").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(
                    "[{'id': 3, 'title':'Why not me?','author': 'The Judds'}]"));
    }


    @Test
    public void testGetOneMusic () throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(CONTROLLER_PATH +"/2").contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().json("{'id': 2, 'title':'Girls just want to have fun','author': 'Cyndi Lauper'}"));
    }

}

