package com.northcoders.RecordShopAPI.service;

import com.northcoders.RecordShopAPI.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecordShopImpl implements RecordShop{

    @Autowired
    AlbumRepository albumRepository;
}
