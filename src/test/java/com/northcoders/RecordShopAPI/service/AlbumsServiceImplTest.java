package com.northcoders.RecordShopAPI.service;

import com.northcoders.RecordShopAPI.DTO.AlbumDTO;
import com.northcoders.RecordShopAPI.DTO.ArtistDTO;
import com.northcoders.RecordShopAPI.exception.DuplicateEntryException;
import com.northcoders.RecordShopAPI.exception.ItemNotFoundException;
import com.northcoders.RecordShopAPI.model.Album;
import com.northcoders.RecordShopAPI.model.Artist;
import com.northcoders.RecordShopAPI.model.Genre;
import com.northcoders.RecordShopAPI.repository.AlbumRepository;
import com.northcoders.RecordShopAPI.repository.ArtistRepository;
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

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@DataJpaTest
class AlbumsServiceImplTest {
    @Mock
    AlbumRepository mockAlbumRepository;

    @Mock
    ArtistRepository mockArtistRepository;

    @InjectMocks
    AlbumsServiceImpl recordShopServiceImpl;
    private static List<Album> albums;
    private static List<AlbumDTO> albumDTO;

    @BeforeAll
    static void beforeAll() {
        albums = new ArrayList<>();
        albums.add(Album.builder().albumId(1)
                .title("Black Sbbath")
                .artist(Artist.builder().artist_id(1L).name("N.I.B").build())
                .genre(Genre.METAL)
                .releaseYear(Year.of(2009))
                .stock(4)
                .price(BigDecimal.valueOf(23.3)).build());
        albums.add(Album.builder()
                .title("Waiting for a train")
                .artist(Artist.builder().artist_id(2L).name("Jimmie Rodgers").build())
                .genre(Genre.COUNTRY)
                .releaseYear(Year.of(2000))
                .stock(14)
                .price(BigDecimal.valueOf(33.983)).build());
        albumDTO= new ArrayList<>();
        albums.forEach(album -> {
            AlbumDTO dto = new AlbumDTO();
            dto.setId(album.getAlbumId());
            dto.setTitle(album.getTitle());
            dto.setReleaseYear(album.getReleaseYear());
            dto.setGenre(album.getGenre());
            dto.setPrice(album.getPrice());
            dto.setStock(album.getStock());
            dto.setArtist(new ArtistDTO(album.getArtist().getName()));
            albumDTO.add(dto);
        });
    }

    @Test
    void getAllAlbumsTest() {
        when(mockAlbumRepository.findAll()).thenReturn(albums);
        List<AlbumDTO> result = recordShopServiceImpl.getAllAlbums();
        assertThat(result).hasSize(2);
        assertThat(result.get(0).getTitle()).isEqualTo(albums.get(0).getTitle());
        assertThat(result.get(0).getId()).isEqualTo(albums.get(0).getAlbumId());
        assertThat(result.get(0).getReleaseYear()).isEqualTo(albums.get(0).getReleaseYear());
    }

    @Test
    void getAlbumByIdTest() {
        when(mockAlbumRepository.findById(2L)).thenReturn(Optional.ofNullable(albums.get(1)));
        var result = recordShopServiceImpl.getAlbumById(2L);
        assertThat(result.getId()).isEqualTo(albums.get(1).getAlbumId());
        assertThat(result.getTitle()).isEqualTo(albums.get(1).getTitle());
    }
    @Test
    void getAlbumByIdTest_AlbumNotFoundException() {
        when(mockAlbumRepository.findById(3L)).thenReturn(Optional.empty());
       assertThrows(ItemNotFoundException.class, ()-> recordShopServiceImpl.getAlbumById(3L));

    }
    @Test
    void saveAlbumTest() {
        when(mockArtistRepository.save(albums.get(1).getArtist())).thenReturn(albums.get(1).getArtist());
        when(mockAlbumRepository.save(albums.get(1))).thenReturn(albums.get(1));
        var result = recordShopServiceImpl.saveAlbum(albums.get(1));
        assertThat(result.getId()).isEqualTo(albums.get(1).getAlbumId());
        assertThat(result.getTitle()).isEqualTo(albums.get(1).getTitle());
    }
    @Test
    void saveAlbumTest_DuplicateEntryException() {
        when(mockArtistRepository.save(albums.get(1).getArtist())).thenReturn(albums.get(1).getArtist());
        when(mockAlbumRepository.save(albums.get(1))).thenThrow( new DuplicateEntryException("Album named Waiting for a train already exists."));

        DuplicateEntryException exception = assertThrows(DuplicateEntryException.class, ()-> {
            recordShopServiceImpl.saveAlbum(albums.get(1));
        });
        assertEquals("Album named Waiting for a train already exists.",exception.getMessage());
        verify(mockAlbumRepository,times(1)).save(albums.get(1));

    }
    @Test
    void updateAlbumTest(){
        Album updatedalbum = Album.builder().albumId(1)
                .title("Black Sbbath")
                .artist(Artist.builder().artist_id(1L).name("N.I.B").build())
                .genre(Genre.METAL)
                .releaseYear(Year.of(2009))
                .stock(10)
                .price(BigDecimal.valueOf(23.3)).build();
        when(mockAlbumRepository.save(albums.get(0))).thenReturn(albums.get(0));
        AlbumDTO album = recordShopServiceImpl.saveAlbum(albums.get(0));
        verify(mockAlbumRepository,times(1)).save(albums.get(0));
        var result = recordShopServiceImpl.updateAlbum(album.getId(),updatedalbum);
        assertThat(result.getId()).isEqualTo(album.getId());
        assertThat(result.getTitle()).isEqualTo(album.getTitle());
        assertThat(result.getStock()).isEqualTo(album.getStock());

    }
}