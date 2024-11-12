package ar.edu.centro8.desarrollo.proyecto.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.centro8.desarrollo.proyecto.models.Cliente;
import ar.edu.centro8.desarrollo.proyecto.repositories.ClienteRepository;

@RestController
@RequestMapping("/api")
public class ClienteController {

    @Autowired
    private ClienteRepository cliRepo;

    @GetMapping("/clientes/mostrar")
    public List<Cliente> mostrarClientes(){
        return cliRepo.findAll();
    }
    
    @PostMapping("/clientes/registrar")
    public String registrarCliente(@RequestBody Cliente cli){
        cliRepo.save(cli);
        return "Cliente registrado correctamente";
    }

    @GetMapping("/clientes/mostrar/{id}")
    public Cliente mostrarrUnCliente(@PathVariable Long id){
        return cliRepo.findById(id).get();
    }

    @DeleteMapping("/clientes/eliminar/{id}")
    public String eliminarUnCliente(@PathVariable Long id){
        cliRepo.deleteById(id);
        return "Cliente eliminado correctamente";
    }

    @PutMapping("/clientes/editar/{id}")
    public String actualizarCliente(@PathVariable Long id, @RequestBody Cliente c){
        Cliente cam = cliRepo.findById(id).get();
        cam.setNombre(c.getNombre());
        cam.setDireccion(c.getDireccion());
        cam.setEmail(c.getEmail());
        cam.setPassword(c.getPassword());
        cam.setTelefono(c.getTelefono());
        cam.setEdad(c.getEdad());
        cliRepo.save(cam);
        return "Datos actualizados correctamente";
    }
}

