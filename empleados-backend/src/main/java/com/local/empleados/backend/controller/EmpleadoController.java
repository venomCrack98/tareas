package com.local.empleados.backend.controller;

import com.local.empleados.backend.model.Empleado;
import com.local.empleados.backend.service.IEmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
    
    @PostMapping("/guardar")
    public String save(Empleado empleado, Model model){
        service.save(empleado);
        return "redirect:/";
    }
    
    @GetMapping("/guardar/{id}")
    public String showById(@PathVariable("id")String id, Model model){
        if(id !=null && !"0".equals(id)){
            model.addAttribute("empleado", service.searchById(id));
        }else{
            model.addAttribute("empleado", new Empleado());
        }
       return "guardar";
    }
    
    @PostMapping("/eliminar/{id}")
    public String deleteEmpleado(@PathVariable("id") String id, Model model){
        service.delete(id);
        return "redirect:/";
    }
}
