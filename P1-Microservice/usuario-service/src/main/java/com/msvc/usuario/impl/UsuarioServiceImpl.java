package com.msvc.usuario.impl;

import com.msvc.usuario.entities.Calificacion;
import com.msvc.usuario.entities.Hotel;
import com.msvc.usuario.entities.Usuario;
import com.msvc.usuario.exceptions.ResourceNotFundException;
import com.msvc.usuario.external.services.HotelService;
import com.msvc.usuario.repository.UsuarioRepository;
import com.msvc.usuario.service.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    private Logger logger = LoggerFactory.getLogger(UsuarioService.class);
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private HotelService hotelService;
    @Override
    public Usuario saveUsuario(Usuario usuario) {
        String randomUsuarioId = UUID.randomUUID().toString();
        usuario.setUsuarioId(randomUsuarioId);
        return repository.save(usuario);
    }

    @Override
    public List<Usuario> getAllUsuario() {
        return repository.findAll();
    }

    /**
     *
     * @param usuarioId
     * @return
     * el metodo de calificaciones se realizara con restTemplate
     * el moto de hotel de realizara con FeignClient
     */
    @Override
    public Usuario getUsuario(String usuarioId) {
        Usuario usuario = repository.findById(usuarioId).orElseThrow(() ->
                new ResourceNotFundException("Usuario no encontrado con el id: "+usuarioId));

        Calificacion[]   calificationByUser = restTemplate
                .getForObject("http://CALIFICACION-SERVICE/calificaciones/usuarios/" + usuario.getUsuarioId(), Calificacion[].class);

        List<Calificacion> calificacions = Arrays.stream(calificationByUser).collect(Collectors.toList());
        List<Calificacion> calificacionList = calificacions.stream().map(calificacion ->{
            System.out.println(calificacion.getHotelId());
            //Restemplate
            ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hoteles/" + calificacion.getHotelId(), Hotel.class);
            Hotel hotel = forEntity.getBody();
            logger.info("Respuesta con codigo de estado: {}", forEntity.getStatusCode());

            //FeignClient-- validar la version del spring boot ya que con la actual no permite funcionar de forma correcta
            //Hotel hotel = hotelService.getHotel(calificacion.getHotelId());
            calificacion.setHotel(hotel);
            return calificacion;
        }).collect(Collectors.toList());

        usuario.setCalificacion(calificacionList);

        return usuario;
    }


}
