package com.northcoders.RecordShopAPI.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Year;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Albums")
public class Album {
    @Id
    @GeneratedValue
    @Column(updatable = false, nullable = false)
    long albumId;

    @Column
    String title;

    @Column
    long artist_id;

    @Column
    String artist;

    @Column
    Year release_year;

    @Column
    long genre_id;

    @Column
    Genre genre;

    @Column
    BigDecimal price;



}
