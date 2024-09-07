package com.northcoders.RecordShopAPI.controller;

import com.northcoders.RecordShopAPI.DTO.AlbumDTO;
import com.northcoders.RecordShopAPI.model.Album;
import com.northcoders.RecordShopAPI.service.AlbumsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/recordshop/albums")
public class AlbumsController {

    @Autowired
    AlbumsService albumsService;

    @GetMapping
    public ResponseEntity<List<AlbumDTO>> getAllAlbums(){
        return new ResponseEntity<>(albumsService.getAllAlbums(), HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Album> getAlbumById(@PathVariable Long id) {
        Album album = albumsService.getALbumById(id);
        return new ResponseEntity<>(album, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Album> addBook(@RequestBody Album album) {
        Album newAlbum = albumsService.saveOrUpdateAlbum(album);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("album", "/api/v1/recordshop/" + newAlbum.getAlbumId());
        return new ResponseEntity<>(newAlbum, httpHeaders, HttpStatus.CREATED);
    }

}
