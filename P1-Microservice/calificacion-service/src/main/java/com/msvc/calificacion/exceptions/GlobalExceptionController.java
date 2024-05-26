package com.msvc.calificacion.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class GlobalExceptionController {
    public ResponseEntity<Map<String, Object>> handlerResourceNotFundException(ResourceNotFundException resourceNotFundException){
        Map map = new HashMap();
        map.put("message", resourceNotFundException.getMessage());
        map.put("success",false);
        map.put("status", HttpStatus.NOT_FOUND);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
    }
}
