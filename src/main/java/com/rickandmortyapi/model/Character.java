package com.rickandmortyapi.model;

import javax.persistence.*;

@Entity
public class Character {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    @Column
    String name;

    @Column(length = 10000)
    String description;

    public Character(String name, String description) {
        this.name = name;
        this.description = description;
    }


    public Character() {
    }
}
