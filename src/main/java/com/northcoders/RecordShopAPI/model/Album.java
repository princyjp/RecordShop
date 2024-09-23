package com.northcoders.RecordShopAPI.model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Year;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Albums")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "albumId")
public class Album {
    @Id
    @GeneratedValue
    @Column(updatable = false, nullable = false)
    long albumId;

    @Column
    String title;

//    @ManyToOne(cascade = CascadeType.MERGE)
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "artist_id")
    //@JsonBackReference
    Artist artist;

    @Column
    Year releaseYear;

//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "genre_id")
    @Column
    Genre genre;

    @Column
    BigDecimal price;

    @Column
    int stock;


}
