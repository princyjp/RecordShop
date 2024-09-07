package com.northcoders.RecordShopAPI.service;

import com.northcoders.RecordShopAPI.model.Album;
import com.northcoders.RecordShopAPI.model.Artist;
import com.northcoders.RecordShopAPI.model.Genre;
import com.northcoders.RecordShopAPI.repository.AlbumRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@DataJpaTest
class AlbumsServiceImplTest {
    @Mock
    AlbumRepository mockAlbumRepository;

    @InjectMocks
    AlbumsServiceImpl recordShopServiceImpl;
    private static List<Album> albums;

    @BeforeAll
    static void beforeAll() {
        albums = new ArrayList<>();
        albums.add(Album.builder()
                .title("Black Sbbath")
                .artist(Artist.builder().artist_id(1L).name("N.I.B").build())
                .genre(Genre.METAL)
                .release_year(Year.of(2009))
                .stock(4)
                .price(BigDecimal.valueOf(23.3)).build());
        albums.add(Album.builder()
                .title("Waiting for a train")
                .artist(Artist.builder().artist_id(2L).name("Jimmie Rodgers").build())
                .genre(Genre.COUNTRY)
                .release_year(Year.of(2000))
                .stock(14)
                .price(BigDecimal.valueOf(33.983)).build());
    }

    @Test
    void getAllAlbumsTest() {
        when(mockAlbumRepository.findAll()).thenReturn(albums);
        List<Album> result = recordShopServiceImpl.getAllAlbums();
        assertThat(result).hasSize(2);
        assertThat(result).isEqualTo(albums);
    }

    @Test
    void getAlbumByIdTest() {
        when(mockAlbumRepository.findById(2L)).thenReturn(Optional.ofNullable(albums.get(1)));
        var result = recordShopServiceImpl.getALbumById(2L);
        assertThat(result).isEqualTo(albums.get(1));
    }
    @Test
    void saveOrUpdateAlbumTest() {
        when(mockAlbumRepository.save(albums.get(1))).thenReturn(albums.get(1));
        var result = recordShopServiceImpl.saveOrUpdateAlbum(albums.get(1));
        assertThat(result).isEqualTo(albums.get(1));
    }
}