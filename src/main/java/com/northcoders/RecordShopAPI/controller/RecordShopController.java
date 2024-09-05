package com.northcoders.RecordShopAPI.controller;

import com.northcoders.RecordShopAPI.model.Album;
import com.northcoders.RecordShopAPI.service.RecordShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/recordshop")
public class RecordShopController {

    @Autowired
    RecordShopService recordShopService;

    @GetMapping
    public ResponseEntity<List<Album>> getAllAlbums(){
        return new ResponseEntity<>(recordShopService.getAllAlbums(), HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Album> getAlbumById(@PathVariable Long id) {
        Album album = recordShopService.getALbumById(id);
        return new ResponseEntity<>(album, HttpStatus.OK);
    }

}
