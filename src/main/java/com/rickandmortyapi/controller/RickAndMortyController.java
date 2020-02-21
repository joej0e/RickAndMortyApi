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
import java.util.stream.Collectors;

@RestController
public class RickAndMortyController {

    @Autowired
    private CharacterRepository characterRepository;

    @GetMapping("/{name}")
    public ResponseEntity<List<String>> findCharacters(@PathVariable(value = "name") String name) {
        Optional<List<Character>> characters = characterRepository.findCharactersByName(name);
        if(characters.isPresent()) {
            List<String> charactersNames = characters.get().stream()
                    .map(Character::getName)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(charactersNames);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/character")
    public ResponseEntity<String> randomCharacter() {
        int randomNum = ThreadLocalRandom.current().nextInt(1, (int) characterRepository.count() + 1);
        Character character = characterRepository.findById(randomNum).get();
        String description = character.getDescription();
        if(description.equals(character.getName())){
            return randomCharacter();
        }
        return ResponseEntity.ok(description);
    }
}
