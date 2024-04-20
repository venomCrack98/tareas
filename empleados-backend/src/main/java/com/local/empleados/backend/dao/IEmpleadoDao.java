package com.local.empleados.backend.dao;

import com.local.empleados.backend.model.Empleado;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IEmpleadoDao extends MongoRepository<Empleado,String>{
    
    
}
