package com.theodo.albeniz.controller;


import org.springframework.http.MediaType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;


import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.theodo.albeniz.services.DataLibraryService;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles(profiles = "data")
public class DataLibraryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired   //pas sure de le mettre, c la fonction de ActiveProfiles non ?
    private DataLibraryService dataLibraryService; 
    @AfterEach   // cleaner library après chaque test, pour que les tests soient vraiement indépendants
    public void clean(){
        dataLibraryService.clean();
    }

    @Test
    public void testgetMusic () throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/library/music").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("[]"));
    }

    @Test
    public void testgetMusicQuery () throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/library/music?query=WHY").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("[]"));
    }


    @Test
    public void testgetOneMusic () throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/library/music/2").contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().string(""));
    }

    @Test
    public void testaddTune () throws Exception {
        //en utilisant l'API GET tester qu'il n'y a rien dans la liste de titre,
        mockMvc.perform(MockMvcRequestBuilders.get("/library/music").contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().string("[]"));

        //appeler l'API POST
        mockMvc.perform(MockMvcRequestBuilders.post("/library/music").contentType(MediaType.APPLICATION_JSON)
        .content("{\"id\": 1, \"title\": \"You belong with me\", \"author\": \"Taylor Swift\"}"))
        .andExpect(MockMvcResultMatchers.status().isOk());

        //vérifier ensuite qu'il y a bien un titre inséré (grace à l'API GET)
        mockMvc.perform(MockMvcRequestBuilders.get("/library/music").contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().json("[{'id': 1, 'title':'You belong with me','author': 'Taylor Swift'}]"));        
    }


}

