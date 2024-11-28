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
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    
    public List<Pedido> obtenerPedidos() {
        return pedidoRepository.findAll();
    }

    public Pedido guardarPedido(Long id_cliente, Pedido pedido) {
        // Verificar que el cliente existe
        Cliente cliente = clienteRepository.findById(id_cliente)
            .orElseThrow(() -> new RuntimeException("Cliente no encontrado con id: " + id_cliente));
        
        // Asociar el cliente al pedido
        pedido.setCliente(cliente);
        
        // Guardar el pedido en la base de datos
        return pedidoRepository.save(pedido);
    }

    public boolean eliminarPedido(Long id) {
        try {
            pedidoRepository.deleteById(id);
            return true; // El pedido se eliminó con éxito
        } catch (Exception e) {
            return false;// No se pudo eliminar el pedido
        }
    }

    public Pedido traerPedido(Long id) {
        return pedidoRepository.findById(id).orElse(null);
    }

    // public Pedido editarPedido(Long id, int nuevaCantidad, String nuevoEstado, String nuevaNotas) {
    //     Pedido pedido = traerPedido(id);
    //     if (pedido != null) {
    //         pedido.setCantidad(nuevaCantidad);
    //         pedido.setEstado(nuevoEstado);
    //         pedido.setNotas(nuevaNotas);
    //         return guardarPedido(pedido);
    //     }
    //     return null;
    // }

    public Cliente guardarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

}



