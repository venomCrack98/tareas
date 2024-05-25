package com.msvc.hotel.impl;

import com.msvc.hotel.entity.Hotel;
import com.msvc.hotel.exceptions.ResourceNotFoundException;
import com.msvc.hotel.repository.HotelRepository;
import com.msvc.hotel.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService {
    @Autowired
    private HotelRepository repository;
    @Override
    public Hotel create(Hotel hotel) {
        String hotelId= UUID.randomUUID().toString();
        hotel.setId(hotelId);
        return repository.save(hotel);
    }

    @Override
    public List<Hotel> getAll() {
        return repository.findAll();
    }

    @Override
    public Hotel get(String id) {
        return repository.findById(id).orElseThrow( ()-> new ResourceNotFoundException("Hotel no encontrado con el Id: "+id));
    }
}
