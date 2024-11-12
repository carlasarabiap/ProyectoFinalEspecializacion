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

import ar.edu.centro8.desarrollo.proyecto.models.Pedido;
import ar.edu.centro8.desarrollo.proyecto.repositories.PedidoRepository;

@RestController
@RequestMapping("/api")
public class PedidoController {

    @Autowired
    private PedidoRepository peRepo;

    @GetMapping("/pedidos/mostrar")
    public List<Pedido> mostrarPedidos(){
        return peRepo.findAll();
    }

    @PostMapping("/pedidos/cargar")
    public String cargarPedido(@RequestBody Pedido pe){
        peRepo.save(pe);
        return "Pedido cargado correctamente";
    }

    @GetMapping("/pedidos/mostrar/{id}")
    public Pedido mostrarUnPedido(@PathVariable Long id){
        return peRepo.findById(id).get();
    }

    @DeleteMapping("/pedidos/eliminar/{id}")
    public String eliminarUnPedido(@PathVariable Long id){
        peRepo.deleteById(id);
        return "Pedido eliminado correctamente";
    }

    @PutMapping("/pedidos/editar/{id}")
    public String actualizarPedido(@PathVariable Long id, @RequestBody Pedido pe){
        Pedido peam = peRepo.findById(id).get();
        peam.setCantidad(pe.getCantidad());
        peam.setEstado(pe.getEstado());
        peam.setNotas(pe.getNotas());
        // Asigna automáticamente la fecha y hora actual
        peam.setFecha(LocalDateTime.now());
        peRepo.save(peam);
        return "Datos actualizados correctamente";
    }
}

