package ar.edu.centro8.desarrollo.proyecto.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.centro8.desarrollo.proyecto.models.Plato;
import ar.edu.centro8.desarrollo.proyecto.repositories.PlatoRepository;

@RestController
@RequestMapping("/api")
public class PlatoController {

    @Autowired
    private PlatoRepository plaRepo;

    @GetMapping("/platos/mostrar")
    public List<Plato> mostrarPlatos(){
        return plaRepo.findAll();
    }

    @PostMapping("/platos/agregar")
    public String agregarPlato(@RequestBody Plato pla){
        plaRepo.save(pla);
        return "Plato agregado correctamente";
    }

    @GetMapping("/platos/mostrar/{id}")
    public Plato mostrarUnPlato(@PathVariable Long id){
        return plaRepo.findById(id).get();
    }

    @DeleteMapping("/platos/eliminar/{id}")
    public String eliminarUnPlato(@PathVariable Long id){
        plaRepo.deleteById(id);
        return "Plato eliminado correctamente";
    }

    @PutMapping("/platos/editar/{id}")
    public String actualizarPlato(@PathVariable Long id, @RequestBody Plato p){
        Plato pam = plaRepo.findById(id).get();
        pam.setNombre(p.getNombre());
        pam.setDescripcion(p.getDescripcion());
        pam.setPrecio(p.getPrecio());
        pam.setCantidad(p.getCantidad());
        pam.setCategoria(p.getCategoria());
        pam.setImagenUrl(p.getImagenUrl());
        plaRepo.save(pam);
        return "Datos actualizados correctamente";
    }
}


