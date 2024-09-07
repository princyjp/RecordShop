package com.northcoders.RecordShopAPI.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

//@Entity
public enum Genre {
    ROCK,
    POP,
    RAP,
    JAZZ,
    COUNTRY,
    CLASSICAL,
    METAL,
    WORLD_MUSIC

//    ROCK(1),
//    POP(2),
//    RAP(3),
//    JAZZ(4),
//    COUNTRY(5),
//    CLASSICAL(6),
//    METAL(7),
//    WORLD_MUSIC(8);

//    @Id
//    @GeneratedValue
//    @Column(updatable = false, nullable = false)
//    final long genre_id;
//
//    Genre(long genre_id){
//        this.genre_id = genre_id;
//    }
}
