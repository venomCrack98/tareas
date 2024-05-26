package com.msvc.calificacion.services;

import com.msvc.calificacion.entity.Calificacion;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CalificacionService {
    Calificacion createCalification(Calificacion calificacion);
    List<Calificacion> getAllCalifications();
    List<Calificacion> getCalificationByUserId(String usuariosId);
    List<Calificacion> getCalificationByHotel(String hotelId);
}
