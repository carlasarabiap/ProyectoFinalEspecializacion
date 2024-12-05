package ar.edu.centro8.desarrollo.proyecto.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.centro8.desarrollo.proyecto.models.Cliente;
import ar.edu.centro8.desarrollo.proyecto.models.Menu;
import ar.edu.centro8.desarrollo.proyecto.models.Pedido;
import ar.edu.centro8.desarrollo.proyecto.models.Plato;
import ar.edu.centro8.desarrollo.proyecto.repositories.ClienteRepository;
import ar.edu.centro8.desarrollo.proyecto.repositories.MenuRepository;
import ar.edu.centro8.desarrollo.proyecto.repositories.PedidoRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private MenuRepository menuRepository;

    /**
     * Obtener todos los pedidos.
     */
    public List<Pedido> obtenerPedidos() {
        return pedidoRepository.findAll();
    }

    /**
     * Crear un pedido asociado a un cliente y generar platos basados en los menús seleccionados.
     * 
     * @param idCliente ID del cliente.
     * @param pedido    Pedido a guardar.
     * @param menuIdsConCantidades Mapa con IDs de menús y cantidades correspondientes.
     * @return El pedido creado con los platos asociados.
     */
    public Pedido crearPedido(Long idCliente, Pedido pedido, Map<Long, Integer> menuIdsConCantidades) {
        // Verificar que el cliente existe
        Cliente cliente = clienteRepository.findById(idCliente)
            .orElseThrow(() -> new RuntimeException("Cliente no encontrado con id: " + idCliente));
        
        // Asociar el cliente al pedido
        pedido.setCliente(cliente);

        // Inicializar los platos del pedido
        List<Plato> platos = new ArrayList<>();

        // Generar platos basados en los menús seleccionados
        menuIdsConCantidades.forEach((menuId, cantidad) -> {
            Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new RuntimeException("Menú no encontrado con id: " + menuId));

            Plato plato = new Plato(cantidad, menu);
            plato.setPedido(pedido);
            platos.add(plato);
        });

        // Asociar los platos al pedido
        pedido.setPlatos(platos);

        // Guardar el pedido junto con los platos
        return pedidoRepository.save(pedido);
    }

    /**
     * Guardar un cliente.
     */
    public Cliente guardarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    /**
     * Eliminar un pedido por ID.
     */
    public boolean eliminarPedido(Long id) {
        try {
            pedidoRepository.deleteById(id);
            return true; // El pedido se eliminó con éxito
        } catch (Exception e) {
            return false; // No se pudo eliminar el pedido
        }
    }

    /**
     * Traer un pedido por ID.
     */
    public Pedido traerPedido(Long id) {
        return pedidoRepository.findById(id).orElse(null);
    }
}
