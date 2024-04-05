package com.theodo.albeniz.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.theodo.albeniz.model.Tune;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.springframework.http.MediaType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import com.theodo.albeniz.services.DataLibraryService;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@ActiveProfiles(profiles = "data")
class DataLibraryControllerTest {

    private static final String CONTROLLER_PATH = "/library/music";
    @Autowired
    MockMvc mockMvc;

    @Autowired
    DataLibraryService dataLibraryService;

    @AfterEach
    public void clean(){
        dataLibraryService.clean();
    }

    @Test
    public void when_get_music_called_with_no_tunes_should_return_empty_list () throws Exception {
        mockMvc.perform(get(CONTROLLER_PATH).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    public void when_get_music_with_query_called_with_no_tunes_should_return_empty_list () throws Exception {
        mockMvc.perform(get(CONTROLLER_PATH +"?query=WHY").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }


    @Test
    public void when_get_one_music_called_with_no_tunes_should_get_empty_string () throws Exception {
        mockMvc.perform(get(CONTROLLER_PATH +"/2").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound())
        .andExpect(content().string(""));
    }

    @Test
    public void when_add_tune_called_with_valid_tune_should_add_this_tune () throws Exception {
        Tune validTune = new Tune(1,"You belong with me", "Taylor Swift");

        mockMvc.perform(get(CONTROLLER_PATH).contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().string("[]"));

        mockMvc.perform(post(CONTROLLER_PATH).contentType(MediaType.APPLICATION_JSON)
        .content(new ObjectMapper().writeValueAsString(validTune)))
        .andExpect(status().isCreated());

        mockMvc.perform(get(CONTROLLER_PATH).contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().json("["+new ObjectMapper().writeValueAsString(validTune)+"]"));
    }


    @Test 
    void when_add_tune_called_with_invalid_title_tune_should_throw_bad_request () throws Exception {
        mockMvc.perform(post(CONTROLLER_PATH).contentType(MediaType.APPLICATION_JSON)
        .content("{\"id\": 1, \"author\": \"Taylor Swift\"}"))
        .andExpect(status().isBadRequest());
    }

    
    @Test
    void test_remove_tune() throws Exception {
        mockMvc.perform(get(CONTROLLER_PATH).contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().string("[]"));

        mockMvc.perform(post(CONTROLLER_PATH).contentType(MediaType.APPLICATION_JSON)
        .content("{\"id\": 1, \"title\": \"You belong with me\", \"author\": \"Taylor Swift\"}"))
        .andExpect(status().isCreated());

        mockMvc.perform(get(CONTROLLER_PATH).contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().json("[{'id': 1, 'title':'You belong with me','author': 'Taylor Swift'}]"));

        mockMvc.perform(delete(CONTROLLER_PATH+"/1").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());

        mockMvc.perform(get(CONTROLLER_PATH).contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().string("[]"));
    }
}

