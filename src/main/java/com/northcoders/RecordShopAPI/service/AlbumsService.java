package com.northcoders.RecordShopAPI.service;

import com.northcoders.RecordShopAPI.DTO.AlbumDTO;
import com.northcoders.RecordShopAPI.model.Album;

import java.util.List;

public interface AlbumsService {
    List<AlbumDTO> getAllAlbums();

    Album getALbumById(Long id);

    Album saveOrUpdateAlbum(Album album);
}
