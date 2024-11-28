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
import ar.edu.centro8.desarrollo.proyecto.services.PlatoService;

@RestController
@RequestMapping("/api/platos")
public class PlatoController {


    @Autowired
    private PlatoService plaServi;
    
        @GetMapping
        public List<Plato> getAllPlatos() {
            return plaServi.obtenerPlatos();
        }
    
        @GetMapping("/{id}")
        public Plato getPlatoById(@PathVariable Long id) {
            return plaServi.traerPlato(id);
        }
    
        @PostMapping
        public void createPlato(@RequestBody Plato plato) {
            plaServi.guardarPlato(plato);
        }
    
        @PutMapping("/{id}")
        public void updatePlato(@PathVariable Long id, @RequestBody Plato plato) {
             plaServi.editarPlato(id, plato.getCantidad());
        }
    
        @DeleteMapping("/{id}")
        public void deletePlato(@PathVariable Long id) {
            plaServi.eliminarPlato(id);
        }
    }