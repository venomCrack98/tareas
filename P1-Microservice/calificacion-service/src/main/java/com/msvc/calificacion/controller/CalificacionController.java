package com.msvc.calificacion.controller;

import com.msvc.calificacion.entity.Calificacion;
import com.msvc.calificacion.services.CalificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/calificaiones")
public class CalificacionController {
    @Autowired
    private CalificacionService service;

    @PostMapping
    public ResponseEntity<Calificacion> saveCalification(@RequestBody Calificacion calificacion){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createCalification(calificacion));
    }
    @GetMapping
    public ResponseEntity<List<Calificacion>> listCalifications(){
        return ResponseEntity.ok(service.getAllCalifications());
    }
    @GetMapping("/usuarios/{usuariosId}")
    public ResponseEntity<List<Calificacion>> getCalificationByUserId(@PathVariable String usuariosId){
        return ResponseEntity.ok(service.getCalificationByUserId(usuariosId));
    }

    @GetMapping("/hoteles/{hotelId}")
    public ResponseEntity<List<Calificacion>> getCalificationByHotelId(@PathVariable String hotelId){
        return ResponseEntity.ok(service.getCalificationByHotel(hotelId));
    }
}
