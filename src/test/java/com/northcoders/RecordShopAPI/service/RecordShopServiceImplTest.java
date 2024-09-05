package com.northcoders.RecordShopAPI.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.northcoders.RecordShopAPI.model.Album;
import com.northcoders.RecordShopAPI.model.Genre;
import com.northcoders.RecordShopAPI.repository.AlbumRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@DataJpaTest
class RecordShopServiceImplTest {
    @Mock
    AlbumRepository mockAlbumRepository;

    @InjectMocks
    RecordShopServiceImpl recordShopServiceImpl;
    private static List<Album> albums;

    @BeforeAll
    static void beforeAll() {
        albums = new ArrayList<>();
        albums.add(Album.builder()
                .title("Black Sbbath")
                .artist("N.I.B")
                .genre(Genre.METAL)
                .release_year(Year.of(2009))
                .stock(4)
                .price(BigDecimal.valueOf(23.3)).build());
        albums.add(Album.builder()
                .title("Waiting for a train")
                .artist("Jimmie Rodgers")
                .genre(Genre.COUNTRY)
                .release_year(Year.of(2000))
                .stock(14)
                .price(BigDecimal.valueOf(33.983)).build());
    }

    @Test
    void getAllAlbums() {
        when(mockAlbumRepository.findAll()).thenReturn(albums);

        List<Album> result = recordShopServiceImpl.getAllAlbums();

        assertThat(result).hasSize(2);
        assertThat(result).isEqualTo(albums);
    }

    @Test
    void getALbumById() {
    }
}