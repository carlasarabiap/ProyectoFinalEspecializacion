package ar.edu.centro8.desarrollo.proyecto.controllers;

import java.time.LocalDateTime;
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

import ar.edu.centro8.desarrollo.proyecto.models.Factura;
import ar.edu.centro8.desarrollo.proyecto.repositories.FacturaRepository;

@RestController
@RequestMapping("/api")
public class FacturaController {
    @Autowired
    private FacturaRepository faRepo;

    @GetMapping("/facturas/mostrar")
    public List<Factura> mostrarFacturas(){
        return faRepo.findAll();
    }

    @PostMapping("/facturas/crear")
    public String crearFactura(@RequestBody Factura fa){
        faRepo.save(fa);
        return "Factura creada correctamente";
    }

    @GetMapping("/facturas/mostrar/{id}")
    public Factura mostrarUnaFactura(@PathVariable Long id){
        return faRepo.findById(id).get();
    }

    @DeleteMapping("/facturas/eliminar/{id}")
    public String eliminarUnaFactura(@PathVariable Long id){
        faRepo.deleteById(id);
        return "Factura eliminada correctamente";
    }

    @PutMapping("/facturas/editar/{id}")
    public String actualizarFactura(@PathVariable Long id, @RequestBody Factura fa){
        Factura fam = faRepo.findById(id).get();
        fam.setEstado(fa.getEstado());
        fam.setTotal(fa.getTotal());
        // Asigna automáticamente la fecha y hora actual
        fam.setFecha(LocalDateTime.now());
        faRepo.save(fam);
        return "Datos actualizados correctamente";
    }
}