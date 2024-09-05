package com.northcoders.RecordShopAPI.service;

import com.northcoders.RecordShopAPI.model.Album;
import com.northcoders.RecordShopAPI.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecordShopServiceImpl implements RecordShopService {

    @Autowired
    AlbumRepository albumRepository;

    @Override
    public List<Album> getAllAlbums() {
        List<Album> albums = new ArrayList<>();
        albumRepository.findAll().forEach(albums::add);
        return albums;
    }

    @Override
    public Album getALbumById(Long id) {
        if(albumRepository.findById(id).isPresent())
        return albumRepository.findById(id).get();

        return null;
    }
}
