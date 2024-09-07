package com.northcoders.RecordShopAPI.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.northcoders.RecordShopAPI.model.Album;
import com.northcoders.RecordShopAPI.model.Artist;
import com.northcoders.RecordShopAPI.model.Genre;
import com.northcoders.RecordShopAPI.service.AlbumsServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@AutoConfigureMockMvc
@SpringBootTest
class AlbumsControllerTest {

    @Mock
    private AlbumsServiceImpl mockrecordShopServiceImpl;

    @InjectMocks
    private AlbumsController albumsController;
    @Autowired
    private MockMvc mockMvcController;
    private ObjectMapper mapper;
    private static List<Album> albums;

    @BeforeEach
    public void setup() {
        mockMvcController = MockMvcBuilders.standaloneSetup(albumsController).build();
        mapper = new ObjectMapper();
    }

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
    void getAllAlbumsTest() throws Exception {
        when(mockrecordShopServiceImpl.getAllAlbums()).thenReturn(albums);

        this.mockMvcController.perform(
                        MockMvcRequestBuilders.get("/api/v1/recordshop"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title").value("Black Sbbath"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].title").value("Waiting for a train"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].stock").value(4))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].stock").value(14));


    }
    @Test
    void getAlbumbyIdTest() throws Exception {
        when(mockrecordShopServiceImpl.getALbumById(1L)).thenReturn(albums.get(0));

        this.mockMvcController.perform(
                        MockMvcRequestBuilders.get("/api/v1/recordshop/1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Black Sbbath"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.stock").value(4));



    }
}