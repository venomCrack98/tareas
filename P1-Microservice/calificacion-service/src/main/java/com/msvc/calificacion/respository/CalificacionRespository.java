package com.msvc.calificacion.respository;

import com.msvc.calificacion.entity.Calificacion;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CalificacionRespository extends MongoRepository<Calificacion, Long> {
   List<Calificacion> findByusuariosId(String usuariosId);
   List<Calificacion> findByHotelId(String hotelId);
}
