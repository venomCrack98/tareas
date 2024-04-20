package com.local.empleados.backend.service;

import com.local.empleados.backend.model.Empleado;
import java.util.List;

public interface IEmpleadoService {
    
    List<Empleado> searchEmpleado();
    Empleado searchById(String id);
    Empleado save(Empleado empleado);
    void delete(String id);
}
