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
@Table(name="platos")
@NoArgsConstructor
public class Plato {
@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="nombre", nullable = false)
    private String nombre;
    
    @Column(name="descripcion", nullable = false)
    private String descripcion;
    
    @Column(name="precio", nullable = false)
    private Double precio;
    
    @Column(name="cantidad", nullable = false)
    private int cantidad;

    @Column(name="categoria", nullable = false)
    private String categoria;
    
    @Column(name="imagenUrl", nullable = false)
    private String imagenUrl;

    public Plato(String nombre, String descripcion, Double precio, int cantidad, String categoria, String imagenUrl) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.cantidad = cantidad;
        this.categoria = categoria;
        this.imagenUrl = imagenUrl;
    }

}
