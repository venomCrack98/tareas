package com.msvc.usuario.external.services;

import com.msvc.usuario.entities.Calificacion;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "CALIFICACION-SERVICE")
public interface CalificacionService {
    @PostMapping
    public ResponseEntity<Calificacion> saveCalification(Calificacion calificacion);

    @PutMapping("/calificaciones/{calificacionId}")
    public ResponseEntity<Calificacion> editCalification(@PathVariable String calificacionId, Calificacion calificacion);

    @DeleteMapping("/calificaciones/{calificacionId}")
    public void deleteCalification(@PathVariable String calificacionId);
}
