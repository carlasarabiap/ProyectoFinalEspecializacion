package ar.edu.centro8.desarrollo.proyecto.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.centro8.desarrollo.proyecto.models.Plato;
import ar.edu.centro8.desarrollo.proyecto.repositories.PlatoRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class PlatoService {

    @Autowired
    private PlatoRepository platoRepo;
    
    public List<Plato> obtenerPlatos() {
        return  platoRepo.findAll();
    }

    public Plato guardarPlato(Plato plato) {
        return platoRepo.save(plato);
    }

    public boolean eliminarPlato(Long id) {
        try {
            platoRepo.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Plato traerPlato(Long id) {
        return platoRepo.findById(id).orElse(null);
    }

    public Plato editarPlato(Long id, int nuevaCantidad) {
        Plato plato = traerPlato(id);
        if (plato != null) {
            plato.setCantidad(nuevaCantidad);
            return guardarPlato(plato);
        }
        return null;
    }


}