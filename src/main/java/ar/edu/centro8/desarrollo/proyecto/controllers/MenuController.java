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

import ar.edu.centro8.desarrollo.proyecto.models.Menu;
import ar.edu.centro8.desarrollo.proyecto.models.Plato;
import ar.edu.centro8.desarrollo.proyecto.services.MenuService;

@RestController
@RequestMapping("/api/menu")
public class MenuController {

    @Autowired
    private  MenuService meServi;
    
        @GetMapping
        public List<Menu> getAllMenu() {
            return meServi.obtenerMenu();
        }
    
        @GetMapping("/{id}")
        public Menu getMenuById(@PathVariable Long id) {
            return meServi.traerMenu(id);
        }
    
        @PostMapping
        public void createMenu(@RequestBody Menu m) {
            meServi.guardarMenu(m);
        }
    
        @PutMapping("/{id}")
        public void updateMenu(@PathVariable Long id, @RequestBody Menu m) {
             meServi.editarMenu(id,m.getNombre(), m.getDescripcion(), m.getPrecio(), m.getCantidad(), m.getCategoria(), m.getImagenUrl());
        }
    
        @DeleteMapping("/{id}")
        public void deleteMenu(@PathVariable Long id) {
            meServi.eliminarMenu(id);
        }
    
        
        //AGREGADO
        @PostMapping("/{id}/platos")
    public ResponseEntity<?> createPlatoForMenu(
        @PathVariable Long id,
        @RequestBody Plato plato
    ) {
        Menu menu = meServi.traerMenu(id);
        if (menu == null) {
            return ResponseEntity.notFound().build();
        }
        
        plato.setMenu(menu);
        meServi.guardarPlato(plato);
        
        return ResponseEntity.ok("Plato creado exitosamente");
    }
      
}