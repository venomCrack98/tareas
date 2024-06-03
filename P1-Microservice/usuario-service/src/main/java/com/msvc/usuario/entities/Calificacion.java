package com.msvc.usuario.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Calificacion {
    private String calificacionId;
    private String usuariosId;
    private String hotelId;
    private int calificacion;
    private String observacion;
    private Hotel hotel;

}
