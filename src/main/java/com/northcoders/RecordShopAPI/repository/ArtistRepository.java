package com.northcoders.RecordShopAPI.repository;

import com.northcoders.RecordShopAPI.model.Artist;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistRepository extends CrudRepository<Artist,Long> {
}
