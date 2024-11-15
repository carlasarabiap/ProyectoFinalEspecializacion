package ar.edu.centro8.desarrollo.proyecto.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.centro8.desarrollo.proyecto.models.Menu;
import ar.edu.centro8.desarrollo.proyecto.models.Plato;
import ar.edu.centro8.desarrollo.proyecto.repositories.MenuRepository;
import ar.edu.centro8.desarrollo.proyecto.repositories.PlatoRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;
    
    @Autowired
    private PlatoRepository platoRepository;
    
        
    public List<Menu> obtenerMenu() {
        return menuRepository.findAll();
    }
    
    public Menu guardarMenu(Menu menu) {
        return menuRepository.save(menu);
    }
    
    public boolean eliminarMenu(Long id) {
        try {
            menuRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public Menu traerMenu(Long id) {
        return menuRepository.findById(id).orElse(null);
    }
    
    public Menu editarMenu(Long id, String nuevoNombre, String nuevaDescripcion, Double nuevoPrecio, int nuevaCantidad, String nuevaCategoria, String nuevaImagenUrl) {
            
        Menu menu = traerMenu(id);
        if (menu != null) {
            menu.setNombre(nuevoNombre);
            menu.setDescripcion(nuevaDescripcion);
            menu.setPrecio(nuevoPrecio);
            menu.setCantidad(nuevaCantidad);
            menu.setCategoria(nuevaCategoria);
            menu.setImagenUrl(nuevaImagenUrl);
            return guardarMenu(menu);
        }
        return null;
    }
    
        public Plato guardarPlato(Plato plato) {
            return platoRepository.save(plato);
        }
    
        
    }
