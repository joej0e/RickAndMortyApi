package com.rickandmortyapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Character {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    private String name;

    @Column(length = 10000)
    private String description;

    public Character(String name, String description) {
        this.name = name;
        this.description = description;
    }
    public Character() {
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
