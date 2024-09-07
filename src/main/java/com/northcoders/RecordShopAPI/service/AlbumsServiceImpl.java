package com.northcoders.RecordShopAPI.service;

import com.northcoders.RecordShopAPI.DTO.AlbumDTO;
import com.northcoders.RecordShopAPI.model.Album;
import com.northcoders.RecordShopAPI.repository.AlbumRepository;
import com.northcoders.RecordShopAPI.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlbumsServiceImpl implements AlbumsService {

    @Autowired
    AlbumRepository albumRepository;

    @Autowired
    ArtistRepository artistRepository;

    @Override
    public List<AlbumDTO> getAllAlbums() {
        List<Album> albums = new ArrayList<>();
        albumRepository.findAll().forEach(albums::add);
        List<AlbumDTO> albumDTO = new ArrayList<>();

        albums.forEach(a -> {
            AlbumDTO dto = new AlbumDTO();
            dto.setId(a.getAlbumId());
            dto.setTitle(a.getTitle());
            albumDTO.add(dto);
        });
        return albumDTO;
    }

    @Override
    public Album getALbumById(Long id) {
        if (albumRepository.findById(id).isPresent())
            return albumRepository.findById(id).get();

        return null;
    }

    @Override
    //@Transactional
    public Album saveOrUpdateAlbum(Album album) {

        if (artistRepository.findByNameIgnoreCase(album.getArtist().getName()) == null)
            artistRepository.save(album.getArtist());

        return albumRepository.save(album);
    }
}
