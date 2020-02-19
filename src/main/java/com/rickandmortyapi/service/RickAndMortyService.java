package com.rickandmortyapi.service;

import com.rickandmortyapi.model.Character;
import com.rickandmortyapi.repository.CharacterRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class RickAndMortyService {

    @Autowired
    private CharacterRepository characterRepository;

    @Scheduled(cron = "0 15 10 15 * ?")
    @PostConstruct
    @Transactional
    public void update() throws IOException {
        Document doc = Jsoup.connect("https://en.wikipedia.org/wiki/List_of_Rick_and_Morty_characters").get();
        Elements charactersNames = doc.select("p b, li>b:first-of-type");
        Elements charactersDescription = doc.select("div+p:has(b), ul li:has(b)");
        List<String> charactersNamesList = charactersNames.eachText();
        List<String> charactersDescriptionList = charactersDescription.eachText();
        charactersDescriptionList = charactersDescriptionList.stream()
                .map(s -> s.replaceAll("\\[(.*?)\\]", ""))
                .collect(Collectors.toList());
        List<Character> characters = new ArrayList<>();
        for (int i = 0; i < charactersNamesList.size(); i++) {
            characters.add(new Character(charactersNamesList.get(i), charactersDescriptionList.get(i)));
        }
        characterRepository.saveAll(characters);
    }

}
