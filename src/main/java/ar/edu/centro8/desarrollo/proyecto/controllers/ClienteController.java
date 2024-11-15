package ar.edu.centro8.desarrollo.proyecto.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.centro8.desarrollo.proyecto.models.Cliente;
import ar.edu.centro8.desarrollo.proyecto.models.Pedido;
import ar.edu.centro8.desarrollo.proyecto.services.ClienteService;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private  ClienteService cliServi;
    
        @GetMapping
        public List<Cliente> getAllCliente() {
            return cliServi.obtenerClientes();
        }
    
        @GetMapping("/{id}")
        public Cliente getClienteById(@PathVariable Long id) {
            return cliServi.traerCliente(id);
        }
    
        @PostMapping
        public void createCliente(@RequestBody Cliente cliente) {
            cliServi.guardarCliente(cliente);
        }
    
        @PutMapping("/{id}")
        public void updateCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
             cliServi.editarCliente(id,cliente.getNombre(), cliente.getEmail(), cliente.getTelefono(), cliente.getDireccion(), cliente.getEdad(), cliente.getPassword());
        }
    
        @DeleteMapping("/{id}")
        public void deleteCliente(@PathVariable Long id) {
            cliServi.eliminarCliente(id);
        }
    
        
        //AGREGADO
        @PostMapping("/{id}/pedidos")
    public ResponseEntity<?> createPedidoForCliente(
        @PathVariable Long id,
        @RequestBody Pedido pedido
    ) {
        Cliente cliente = cliServi.traerCliente(id);
        if (cliente == null) {
            return ResponseEntity.notFound().build();
        }
        
        pedido.setCliente(cliente);
        cliServi.guardarPedido(pedido);
        
        return ResponseEntity.ok("Pedido creado exitosamente");
    }
      
}