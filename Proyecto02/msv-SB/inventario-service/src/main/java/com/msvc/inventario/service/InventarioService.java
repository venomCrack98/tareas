package com.msvc.inventario.service;

import com.msvc.inventario.repository.InventarioRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InventarioService {

    @Autowired
    private InventarioRespository inventarioRespository;
    @Transactional(readOnly = true)
    public boolean isInStock(String codigoSku){
        return inventarioRespository.findByCodigoSku(codigoSku).isPresent();
    }
}
