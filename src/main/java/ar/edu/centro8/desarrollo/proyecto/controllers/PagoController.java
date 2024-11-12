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

import ar.edu.centro8.desarrollo.proyecto.models.Pago;
import ar.edu.centro8.desarrollo.proyecto.repositories.PagoRepository;

@RestController
@RequestMapping("/api")
public class PagoController {
    @Autowired
    private PagoRepository paRepo;
    
    @GetMapping("/pagos/mostrar")
    public List<Pago> mostrarPagos(){
        return paRepo.findAll();
    }

    @PostMapping("/pagos/agregar")
    public String agregarPago(@RequestBody Pago pa){
        paRepo.save(pa);
        return "Pago agregado correctamente";
    }

    @GetMapping("/pagos/mostrar/{id}")
    public Pago mostrarUnPago(@PathVariable Long id){
        return paRepo.findById(id).get();

    }

    @DeleteMapping("/pagos/eliminar/{id}")
    public String eliminarUnPago(@PathVariable Long id){
        paRepo.deleteById(id);
        return "Pago eliminado correctamente";
    }

    @PutMapping("/pagos/editar/{id}")
    public String actualizarPago(@PathVariable Long id, @RequestBody Pago pa){
        Pago paam = paRepo.findById(id).get();
        paam.setTipo(pa.getTipo());
        paRepo.save(paam);
        return "Datos actualizados correctamente";
    }
}