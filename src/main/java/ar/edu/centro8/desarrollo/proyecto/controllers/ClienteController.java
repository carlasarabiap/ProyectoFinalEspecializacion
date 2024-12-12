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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.centro8.desarrollo.proyecto.DTO.ClienteDto;
import ar.edu.centro8.desarrollo.proyecto.DTO.LoginClienteDTO;
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

    // @PostMapping
    // public void createCliente(@RequestBody Cliente cliente) {
    //     cliServi.guardarCliente(cliente);
    // }
    
    // @PostMapping
    // public ResponseEntity<?> createCliente(@RequestBody Cliente cliente) {
    //     try {
    //         cliServi.guardarCliente(cliente);
    //         return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", "Cliente creado con éxito"));
    //     } catch (Exception e) {
    //         return ResponseEntity.status(HttpStatus.BAD_REQUEST)
    //                              .body(Map.of("message", "Error al guardar el cliente"));
    //     }
    // }
    
    @PostMapping
    public ResponseEntity<?> createCliente(@RequestBody Cliente cliente) {
        try {
            Cliente clienteGuardado = cliServi.guardarCliente(cliente);
            return ResponseEntity.status(HttpStatus.CREATED).body(clienteGuardado); // Devuelve el cliente guardado como JSON
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                .body(Map.of("message", "Error al guardar el cliente"));
        }
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

            
        return ResponseEntity.ok("Pedido creado exitosamente");
    }
      
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody LoginClienteDTO loginClienteDto) {
        Map<String, Object> response = new HashMap<>();
        try {
            // Intentar autenticar al cliente
            ClienteDto cliente = cliServi.login(loginClienteDto);

            response.put("message", "Sesión Iniciada");
            response.put("cliente", cliente);

            return ResponseEntity.ok(response); // Respuesta 200 OK con datos
        } catch (IllegalArgumentException e) {
            // Manejo de error si las credenciales son incorrectas
            response.put("message", "Credenciales incorrectas");
            response.put("cliente", null);

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response); // Respuesta 401 Unauthorized
        }
    }

}