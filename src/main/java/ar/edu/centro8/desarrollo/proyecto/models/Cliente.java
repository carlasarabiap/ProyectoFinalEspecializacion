package ar.edu.centro8.desarrollo.proyecto.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name="clientes")
@NoArgsConstructor
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name="nombre", nullable = false)
    private String nombre;
    
    @Column(name="email", nullable = false)
    private String email;

    @Column(name="telefono", nullable = false)
    private int telefono;
    
    @Column(name="direccion", nullable = false)
    private String direccion;

    @Column(name="edad", nullable = false)
    private int edad;
    
    @Column(name="password", nullable = false)
    private String password;

    public Cliente(String nombre, String email, int telefono, String direccion, int edad,
            String password) {
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.direccion = direccion;
        this.edad = edad;
        this.password = password;
    }
    
}
