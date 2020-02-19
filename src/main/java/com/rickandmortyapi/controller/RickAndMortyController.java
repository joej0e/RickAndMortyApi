package com.rickandmortyapi.controller;

import com.rickandmortyapi.model.Character;
import com.rickandmortyapi.repository.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@RestController
public class RickAndMortyController {

    @Autowired
    private CharacterRepository characterRepository;

    @GetMapping("/{name}")
    public ResponseEntity<List<Character>> findCharacters(@PathVariable(value = "name") String name) {
        Optional<List<Character>> characters = characterRepository.findCharactersByName(name);
        return characters.map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/character")
    public ResponseEntity<Character> randomCharacter() {
        int randomNum = ThreadLocalRandom.current().nextInt(0, (int) characterRepository.count() + 1);
        return ResponseEntity.ok(characterRepository.findById(randomNum).get());
    }
}
