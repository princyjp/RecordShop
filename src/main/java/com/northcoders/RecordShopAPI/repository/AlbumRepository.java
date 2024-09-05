package com.northcoders.RecordShopAPI.repository;

import com.northcoders.RecordShopAPI.model.Album;
import org.springframework.data.repository.CrudRepository;

public interface AlbumRepository extends CrudRepository<Album,Long> {
}
