package com.msvc.calificacion.impl;

import com.msvc.calificacion.entity.Calificacion;
import com.msvc.calificacion.respository.CalificacionRespository;
import com.msvc.calificacion.services.CalificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
public class CalificacionServiceImpl implements CalificacionService {
    @Autowired
    private CalificacionRespository respository;
    @Override
    public Calificacion createCalification(Calificacion calificacion) {
        String calificacionId = UUID.randomUUID().toString();
            calificacion.setCalificacionId(calificacionId);
            return respository.save(calificacion);
    }

    @Override
    public List<Calificacion> getAllCalifications() {
        return respository.findAll();
    }

    @Override
    public List<Calificacion> getCalificationByUserId(String usuariosId) {
        return respository.findByusuariosId(usuariosId);
    }

    @Override
    public List<Calificacion> getCalificationByHotel(String hotelId) {

        return respository.findByHotelId(hotelId);
    }
}
