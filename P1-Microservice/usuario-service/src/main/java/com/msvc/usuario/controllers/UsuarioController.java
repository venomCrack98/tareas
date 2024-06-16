package com.msvc.usuario.controllers;

import com.msvc.usuario.entities.Usuario;
import com.msvc.usuario.service.UsuarioService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;


@Slf4j
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService service;

    @PostMapping
    public ResponseEntity<Usuario> guardarUsuario(@RequestBody Usuario usuarioRequest){
        Usuario usuario =service.saveUsuario(usuarioRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuario(){
        List<Usuario> usuarios = service.getAllUsuario();
        return ResponseEntity.ok(usuarios);
    }

    int retryCount = 1;
    @GetMapping("/{usuarioid}")
    //@CircuitBreaker(name ="ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
    @Retry(name = "ratingHotelService", fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<Usuario> obtenerUsuario(@PathVariable String usuarioid){
        log.info("Listar un solo usuario: usuarioController");
        log.info("Cantidad reintentos : {}", retryCount);
        retryCount ++;
        Usuario usuario= service.getUsuario(usuarioid);
        return ResponseEntity.ok(usuario);
    }
    public ResponseEntity<Usuario> ratingHotelFallback(String usuarioId, Exception exception){
        log.info("El  respaldo se ejecuta porque el servicio esta inactivo");
        Usuario usuario = Usuario.builder()
                .email("root@Gmail.com")
                .nombre("root")
                .informacion("Este usuario se crea por defecto cuando un servicio se cae")
                .usuarioId("1234")
                .build();
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }
}
