package com.msvc.inventario.repository;

import com.msvc.inventario.model.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InventarioRespository extends JpaRepository<Inventario, Long> {
    Optional<Inventario> findByCodigoSku(String codigoSku);
}
