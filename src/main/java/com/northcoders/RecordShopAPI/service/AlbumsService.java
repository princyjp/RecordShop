package com.northcoders.RecordShopAPI.service;

import com.northcoders.RecordShopAPI.DTO.AlbumDTO;
import com.northcoders.RecordShopAPI.model.Album;

import java.util.List;

public interface AlbumsService {
    List<AlbumDTO> getAllAlbums();

    AlbumDTO getAlbumById(Long id);

    AlbumDTO saveAlbum(Album album);

    AlbumDTO updateAlbum(Long id, Album album);

    void deleteAlbum(Long id);
}
