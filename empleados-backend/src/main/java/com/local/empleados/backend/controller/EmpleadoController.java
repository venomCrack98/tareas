package com.local.empleados.backend.controller;

import com.local.empleados.backend.service.IEmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class EmpleadoController {
    @Autowired
    private IEmpleadoService service;
    
    @RequestMapping("/")
    public String index(Model model){
        model.addAttribute("list",service.searchEmpleado());
        return "index";
    }
}
