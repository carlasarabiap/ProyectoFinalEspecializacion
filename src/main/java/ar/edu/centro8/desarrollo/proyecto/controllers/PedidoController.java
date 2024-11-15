package ar.edu.centro8.desarrollo.proyecto.controllers;

//import java.time.LocalDateTime;
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
import ar.edu.centro8.desarrollo.proyecto.services.PedidoService;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pediServi;
    
        @GetMapping
        public List<Pedido> getAllPedidos() {
            return pediServi.obtenerPedidos();
        }
    
        @GetMapping("/{id}")
        public Pedido getPedidoById(@PathVariable Long id) {
            return pediServi.traerPedido(id);
        }
    
        @PostMapping
        public void createPedido(@RequestBody Pedido pedido) {
            pediServi.guardarPedido(pedido);
        }
    
        @PutMapping("/{id}")
        public void updatePedido(@PathVariable Long id, @RequestBody Pedido pedido) {
             pediServi.editarPedido(id, pedido.getCantidad(), pedido.getEstado(), pedido.getNotas());
        }
    
        @DeleteMapping("/{id}")
        public void deletePedido(@PathVariable Long id) {
            pediServi.eliminarPedido(id);
        }
    }
    