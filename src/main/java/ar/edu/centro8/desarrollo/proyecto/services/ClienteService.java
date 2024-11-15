package ar.edu.centro8.desarrollo.proyecto.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.centro8.desarrollo.proyecto.models.Cliente;
import ar.edu.centro8.desarrollo.proyecto.models.Pedido;
import ar.edu.centro8.desarrollo.proyecto.repositories.ClienteRepository;
import ar.edu.centro8.desarrollo.proyecto.repositories.PedidoRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ClienteService {
        @Autowired
        private ClienteRepository clienteRepository;
    
        @Autowired
        private PedidoRepository pedidoRepository;
    
        
        public List<Cliente> obtenerClientes() {
            return clienteRepository.findAll();
        }
    
        public Cliente guardarCliente(Cliente cliente) {
            return clienteRepository.save(cliente);
        }
    
        public boolean eliminarCliente(Long id) {
            try {
                clienteRepository.deleteById(id);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
    
        public Cliente traerCliente(Long id) {
            return clienteRepository.findById(id).orElse(null);
        }
    
        public Cliente editarCliente(Long id, String nuevoNombre, String nuevoEmail, int nuevoTelefono, String nuevaDireccion, int nuevaEdad, String nuevoPassword) {
            Cliente cliente = traerCliente(id);
            if (cliente != null) {
                cliente.setNombre(nuevoNombre);
                cliente.setEmail(nuevoEmail);
                cliente.setTelefono(nuevoTelefono);
                cliente.setDireccion(nuevaDireccion);
                cliente.setEdad(nuevaEdad);
                cliente.setPassword(nuevoPassword);
                return guardarCliente(cliente);
            }
            return null;
        }
    
        public Pedido guardarPedido(Pedido pedido) {
            return pedidoRepository.save(pedido);
        }
    
        
    }
