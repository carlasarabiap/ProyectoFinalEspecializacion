package ar.edu.centro8.desarrollo.proyecto.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.centro8.desarrollo.proyecto.DTO.CrearPedidoDto;
import ar.edu.centro8.desarrollo.proyecto.models.Pedido;
import ar.edu.centro8.desarrollo.proyecto.services.PedidoService;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public List<Pedido> getAllPedidos() {
        return pedidoService.obtenerPedidos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> getPedidoById(@PathVariable Long id) {
        Pedido pedido = pedidoService.traerPedido(id);
        if (pedido != null) {
            return ResponseEntity.ok(pedido);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping("/clientes/{id_cliente}")
    public ResponseEntity<?> createPedido(
            @PathVariable("id_cliente") Long idCliente,
            @RequestBody CrearPedidoDto request) {
        try {
            if (request.getMenuIdsConCantidades() == null || request.getMenuIdsConCantidades().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El carrito no puede estar vacío.");
            }            
    
            if (request.getNotas() == null || request.getNotas().isEmpty()) {
                request.setNotas("Pedido sin notas.");
            }

            Pedido nuevoPedido = new Pedido();
            nuevoPedido.setNotas(request.getNotas());

            Pedido pedidoCreado = pedidoService.crearPedido(idCliente, nuevoPedido, request.getMenuIdsConCantidades());

            // Crear una respuesta que solo incluya el id_pedido
            Map<String, Object> response = new HashMap<>();
            response.put("id_pedido", pedidoCreado.getId());
            response.put("mensaje", "Pedido creado con éxito");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al crear el pedido: " + e.getMessage());
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePedido(@PathVariable Long id) {
        if (pedidoService.eliminarPedido(id)) {
            return ResponseEntity.ok("Pedido eliminado exitosamente");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pedido no encontrado");
    }
}