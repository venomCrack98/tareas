package com.local.empleados.backend.service.impl;

import com.local.empleados.backend.dao.IEmpleadoDao;
import com.local.empleados.backend.model.Empleado;
import com.local.empleados.backend.service.IEmpleadoService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpleadoServiceImpl implements IEmpleadoService{
    
    @Autowired
    private IEmpleadoDao empleadoDao;
    
    @Override
    public List<Empleado> searchEmpleado() {
        
        return empleadoDao.findAll();
    }

    @Override
    public Empleado searchById(String id) {
        Optional <Empleado> empleado = empleadoDao.findById(id);
        if(empleado.isPresent()){
            return empleado.get();
        }
        return empleado.get();
    }

    @Override
    public Empleado save(Empleado empleado) {
        return empleadoDao.save(empleado);
    }

    @Override
    public void delete(String id) {
        empleadoDao.deleteById(id);
    }
    
}
