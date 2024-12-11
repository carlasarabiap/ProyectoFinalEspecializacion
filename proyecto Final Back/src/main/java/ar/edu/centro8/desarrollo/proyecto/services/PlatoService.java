package ar.edu.centro8.desarrollo.proyecto.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.centro8.desarrollo.proyecto.models.Pedido;
import ar.edu.centro8.desarrollo.proyecto.models.Plato;
import ar.edu.centro8.desarrollo.proyecto.repositories.PedidoRepository;
import ar.edu.centro8.desarrollo.proyecto.repositories.PlatoRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class PlatoService {

    @Autowired
    private PlatoRepository plaRepo;
    
    @Autowired
    private PedidoRepository peRepo;

    public List<Plato> obtenerPlatos() {
        return  plaRepo.findAll();
    }

    public Plato guardarPlato(Plato plato) {
        return plaRepo.save(plato);
    }

    public boolean eliminarPlato(Long id) {
        try {
            plaRepo.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Plato traerPlato(Long id) {
        return plaRepo.findById(id).orElse(null);
    }

    public Plato editarPlato(Long id, int nuevaCantidad) {
        Plato plato = traerPlato(id);
        if (plato != null) {
            plato.setCantidad(nuevaCantidad);
            return guardarPlato(plato);
        }
        return null;
    }

public Pedido guardarPedido(Pedido pedido) {
        return peRepo.save(pedido);
    }

}
