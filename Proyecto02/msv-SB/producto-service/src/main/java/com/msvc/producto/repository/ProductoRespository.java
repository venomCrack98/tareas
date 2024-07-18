package com.msvc.producto.repository;

import com.msvc.producto.model.Producto;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductoRespository extends MongoRepository<Producto, String> {
}
