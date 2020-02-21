package com.rickandmortyapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
class RickAndMortyApiApplicationTests {

    @Autowired
    private MockMvc mvc;

    @Test
    public void findCharactersProperName() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        List<String> charactersNames = new ArrayList<>();
        charactersNames.add("Mortimer \"Morty\" Smith");
        charactersNames.add("Evil Morty");
        charactersNames.add("Cronenberg Rick and Morty");
        charactersNames.add("Campaign Manager Morty");
        charactersNames.add("Cop Rick and Cop Morty");
        charactersNames.add("Mortimer \"Morty\" Smith Jr.");
        String jsonCharactersNames = mapper.writeValueAsString(charactersNames);
        mvc.perform(get("/morty")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(jsonCharactersNames));
    }

    @Test
    public void findCharactersNonExistingName() throws Exception {
        mvc.perform(get("/smwmsoqqq")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getRandomCharacterDescription() throws Exception {
        mvc.perform(get("/character")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

}
