package ar.edu.centro8.desarrollo.proyecto.models;


import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name="menu")
@NoArgsConstructor
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_menu", nullable = false)
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

    //RELACION MENU - PLATO
    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL, orphanRemoval = true)
    // @JsonManagedReference
    @JsonIgnore
    private List<Plato> platos;



    public Menu(String nombre, String descripcion, Double precio, int cantidad, String categoria, String imagenUrl) {
    this.nombre = nombre;
    this.descripcion = descripcion;
    this.precio = precio;
    this.cantidad = cantidad;
    this.categoria = categoria;
    this.imagenUrl = imagenUrl;
    this.platos = new ArrayList<>();
}


    //AGREGADO
    public Plato agregarPlato(Plato plato) {
        this.platos.add(plato);
        return plato;
    }

}