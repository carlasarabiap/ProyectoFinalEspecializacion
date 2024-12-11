package ar.edu.centro8.desarrollo.proyecto.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ar.edu.centro8.desarrollo.proyecto.models.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    @Query("SELECT c FROM Cliente c WHERE c.email = :email AND c.password = :password")
    Cliente findByEmailAndPassword(@Param("email") String email, @Param("password") String password);
}

