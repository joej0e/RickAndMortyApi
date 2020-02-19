package com.rickandmortyapi.repository;

import com.rickandmortyapi.model.Character;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CharacterRepository extends JpaRepository<Character, Integer> {
    @Query(value = "select * from character c where c.name ilike %?1%", nativeQuery = true)
    Optional<List<Character>> findCharactersByName(String name);
}
