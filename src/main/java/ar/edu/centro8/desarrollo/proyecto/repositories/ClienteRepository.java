package ar.edu.centro8.desarrollo.proyecto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ar.edu.centro8.desarrollo.proyecto.models.Cliente;

public interface ClienteRepository extends JpaRepository <Cliente, Long>{

}
