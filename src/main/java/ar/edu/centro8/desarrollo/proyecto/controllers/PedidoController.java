package ar.edu.centro8.desarrollo.proyecto.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

        @PostMapping("/clientes/{id_cliente}")
        public ResponseEntity<String> createPedido(@PathVariable Long id_cliente, @RequestBody Pedido pedido) {
            try {
                pediServi.guardarPedido(id_cliente, pedido);
                return ResponseEntity.status(201).body("Pedido creado exitosamente");
            } catch (Exception e) {
                return ResponseEntity.status(400).body("Error al crear el pedido: " + e.getMessage());
            }
        }
    
        // @PutMapping("/{id}")
        // public void updatePedido(@PathVariable Long id, @RequestBody Pedido pedido) {
        //      pediServi.editarPedido(id, pedido.getCantidad(), pedido.getEstado(), pedido.getNotas());
        // }
    
        @DeleteMapping("/{id}")
        public void deletePedido(@PathVariable Long id) {
            pediServi.eliminarPedido(id);
        }
    }