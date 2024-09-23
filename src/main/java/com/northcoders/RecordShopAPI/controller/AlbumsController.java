package com.northcoders.RecordShopAPI.controller;

import com.northcoders.RecordShopAPI.DTO.AlbumDTO;
import com.northcoders.RecordShopAPI.exception.ItemNotFoundException;
import com.northcoders.RecordShopAPI.model.Album;
import com.northcoders.RecordShopAPI.service.AlbumsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
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
    public ResponseEntity<AlbumDTO> getAlbumById(@PathVariable Long id){
        return new ResponseEntity<>(albumsService.getAlbumById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AlbumDTO> addAlbum(@RequestBody Album album) {
        AlbumDTO newAlbum = albumsService.saveAlbum(album);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("album", "/api/v1/recordshop/albums/" + newAlbum.getId());
        return new ResponseEntity<>(newAlbum, httpHeaders, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlbumDTO> updateAlbum(@PathVariable Long id, @RequestBody Album album) {
            AlbumDTO updatedAlbum = albumsService.updateAlbum(id, album);
            return new ResponseEntity<>(updatedAlbum, HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAlbum(@PathVariable Long id){
        albumsService.deleteAlbum(id);
        return new ResponseEntity<>("Deleted",HttpStatus.OK);
    }

}
