package com.northcoders.RecordShopAPI.service;

import com.northcoders.RecordShopAPI.DTO.AlbumDTO;
import com.northcoders.RecordShopAPI.DTO.ArtistDTO;
import com.northcoders.RecordShopAPI.DTO.ObjectMerger;
import com.northcoders.RecordShopAPI.exception.DuplicateEntryException;
import com.northcoders.RecordShopAPI.exception.ItemNotFoundException;
import com.northcoders.RecordShopAPI.exception.MissingFieldValueException;
import com.northcoders.RecordShopAPI.model.Album;
import com.northcoders.RecordShopAPI.model.Artist;
import com.northcoders.RecordShopAPI.repository.AlbumRepository;
import com.northcoders.RecordShopAPI.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AlbumsServiceImpl implements AlbumsService {

    @Autowired
    AlbumRepository albumRepository;

    @Autowired
    ArtistRepository artistRepository;

    private static AlbumDTO getAlbumDTO(Album album) {
        AlbumDTO dto = new AlbumDTO();
        try {
            dto.setId(album.getAlbumId());
            dto.setTitle(album.getTitle());
            dto.setReleaseYear(album.getReleaseYear());
            dto.setGenre(album.getGenre());
            dto.setPrice(album.getPrice());
            dto.setStock(album.getStock());
            dto.setArtist(new ArtistDTO(album.getArtist().getName()));
            return dto;
        }catch (NullPointerException e){
            throw new MissingFieldValueException("Missing Fields in Album. Please provide following fields: title, artist, releaseYear, genre, price, stock");
        }
    }

    @Override
    public List<AlbumDTO> getAllAlbums() {
        List<Album> albums = new ArrayList<>();
        albumRepository.findAll().forEach(albums::add);
        List<AlbumDTO> albumDTO = new ArrayList<>();
        albums.forEach(album -> {
            AlbumDTO dto = getAlbumDTO(album);
            albumDTO.add(dto);
        });
        return albumDTO;
    }

    @Override
    public AlbumDTO getAlbumById(Long id) {
        Optional<Album> album = albumRepository.findById(id);
        if (album.isEmpty()) throw new ItemNotFoundException(String.format("Album with id '%s' cannot be found.", id));
        return getAlbumDTO(album.get());
    }

    @Override
    //@Transactional
    public AlbumDTO saveAlbum(Album newAlbum) {
//        Album savedAlbum = new Album();
        Artist artist = artistRepository.findByNameIgnoreCase(newAlbum.getArtist().getName());
        if (artist != null) {
            newAlbum.setArtist(artist);
            artist.getAlbums().forEach(album -> {
                if (album.getTitle().equals(newAlbum.getTitle())) {
                    throw new DuplicateEntryException(String.format("Album named '%s' already exists.", album.getTitle()));
                }
            });
        }

        Album savedAlbum = albumRepository.save(newAlbum);


        return getAlbumDTO(savedAlbum);
    }

    @Override
    public AlbumDTO updateAlbum(Long id, Album album) {
        Optional<Album> oldAlbum = albumRepository.findById(id);
        if (oldAlbum.isEmpty()) throw new ItemNotFoundException(String.format("Album with id '%s' cannot be found.", id));
        Album updatedAlbum = oldAlbum.get();
        isValidated(album);

            album.setAlbumId(id);
            ObjectMerger.merge(updatedAlbum, album);


        return getAlbumDTO(albumRepository.save(updatedAlbum));

    }

    @Override
    public void deleteAlbum(Long id) {
        Optional<Album> album = albumRepository.findById(id);
        if (album.isEmpty()) throw new ItemNotFoundException(String.format("Album with id '%s' cannot be found.", id));
        albumRepository.deleteById(id);
    }

    private void isValidated(Album album) {
        if (album == null) throw new ItemNotFoundException("Fields cannot be empty");
//        if(album.getGenre() != null){
//
//        }
    }
}
