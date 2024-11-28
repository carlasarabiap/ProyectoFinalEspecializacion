package ar.edu.centro8.desarrollo.proyecto.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.centro8.desarrollo.proyecto.models.Factura;
import ar.edu.centro8.desarrollo.proyecto.models.Pedido;
import ar.edu.centro8.desarrollo.proyecto.repositories.FacturaRepository;
import ar.edu.centro8.desarrollo.proyecto.repositories.PedidoRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class FacturaService {
    @Autowired
    private FacturaRepository facturaRepo;

    @Autowired
    private PedidoRepository peRepo;
    
    public List<Factura> obtenerFacturas() {
        return  facturaRepo.findAll();
    }

    public Pedido obtenerPedidoPorId(Long id_pedido) {
        return peRepo.findById(id_pedido)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado con ID: " + id_pedido));
    }

    public Factura crearFactura(Long id_pedido) {
        // Utilizar el nuevo método para buscar el pedido.
        Pedido pedido = obtenerPedidoPorId(id_pedido);

        // Crea la factura y asocia el pedido
        Factura factura = new Factura();
        factura.setPedido(pedido);

        // Guarda la factura en la base de datos
        
        return guardarFactura(factura);
    }
    
    public Factura guardarFactura(Factura factura) {
        facturaRepo.save(factura);
        return factura;
    }

    public boolean eliminarFactura(Long id) {
        try {
            facturaRepo.deleteById(id);
            return true;// La factura se eliminó con éxito
        } catch (Exception e) {
            return false;// No se pudo eliminar la factura
        }
    }

    public Factura traerFactura(Long id) {
        return facturaRepo.findById(id).orElse(null);
    }

    public Factura editarFactura(Long id, Double nuevoTotal, String nuevoEstado) {
        Factura factura = traerFactura(id);
        if (factura != null) {
            factura.setTotal(nuevoTotal);
            factura.setEstado(nuevoEstado);
            return guardarFactura(factura);
        }
        return null;
    }


}
