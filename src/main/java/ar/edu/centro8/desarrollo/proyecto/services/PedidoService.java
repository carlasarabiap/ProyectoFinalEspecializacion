package ar.edu.centro8.desarrollo.proyecto.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.centro8.desarrollo.proyecto.models.Pedido;
import ar.edu.centro8.desarrollo.proyecto.repositories.PedidoRepository;
import jakarta.transaction.Transactional;
 
@Service
@Transactional
public class PedidoService {
    @Autowired
    private PedidoRepository pedidoRepo;
    
    public List<Pedido> obtenerPedidos() {
        return  pedidoRepo.findAll();
    }

    public Pedido guardarPedido(Pedido pedido) {
        return pedidoRepo.save(pedido);
    }

    public boolean eliminarPedido(Long id) {
        try {
            pedidoRepo.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Pedido traerPedido(Long id) {
        return pedidoRepo.findById(id).orElse(null);
    }

    public Pedido editarPedido(Long id, int nuevaCantidad, String nuevoEstado, String nuevaNota) {
        Pedido pedido = traerPedido(id);
        if (pedido != null) {
            pedido.setCantidad(nuevaCantidad);
            pedido.setEstado(nuevoEstado);
            pedido.setNotas(nuevaNota);
            return guardarPedido(pedido);
        }
        return null;
    }


}






// @Service
// @Transactional
// public class PedidoService {

//     @Autowired
//     private PedidoRepository pediRepo;
    
        
//     public List<Pedido> obtenerPedidos() {
//         return  pediRepo.findAll();
//     }
    
//         public Pedido guardarPedido(Pedido pedido) {
//             return pediRepo.save(pedido);
//         }
    
//         public boolean eliminarPedido(Long id) {
//             try {
//                 pediRepo.deleteById(id);
//                 return true;
//             } catch (Exception e) {
//                 return false;
//             }
//         }
    
//         public Pedido traerPedido(Long id) {
//             return pediRepo.findById(id).orElse(null);
//         }
    
//         public Pedido editarPedido(Long id, int nuevaCantidad, String nuevoEstado, String nuevaNota) {
//             Pedido pedido = traerPedido(id);
//             if (pedido != null) {
//                 pedido.setCantidad(nuevaCantidad);
//                 pedido.setEstado(nuevoEstado);
//                 pedido.setNotas(nuevaNota);
//                 return guardarPedido(pedido);
//             }
//             return null;
//         }
//     }