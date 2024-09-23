package com.northcoders.RecordShopAPI.DTO;

import com.northcoders.RecordShopAPI.model.Genre;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Year;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlbumDTO {
    long id;
    String title;
    Year releaseYear;
    Genre genre;
    BigDecimal price;
    int stock;
    ArtistDTO artist;
}
