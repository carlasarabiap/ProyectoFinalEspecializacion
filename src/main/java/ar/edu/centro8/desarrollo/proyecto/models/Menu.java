package ar.edu.centro8.desarrollo.proyecto.models;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

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
    @JsonManagedReference
    private List<Plato> platos;


    public Menu(String nombre, String descripcion, Double precio, int cantidad, String categoria, String imagenUrl,
            List<Plato> platos) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.cantidad = cantidad;
        this.categoria = categoria;
        this.imagenUrl = imagenUrl;
        this.platos = platos;
    }





    //AGREGADO
    public Plato agregarPlato(Plato plato) {
        this.platos.add(plato);
        return plato;
    }





    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
        result = prime * result + ((descripcion == null) ? 0 : descripcion.hashCode());
        result = prime * result + ((precio == null) ? 0 : precio.hashCode());
        result = prime * result + cantidad;
        result = prime * result + ((categoria == null) ? 0 : categoria.hashCode());
        result = prime * result + ((imagenUrl == null) ? 0 : imagenUrl.hashCode());
        result = prime * result + ((platos == null) ? 0 : platos.hashCode());
        return result;
    }





    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Menu other = (Menu) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (nombre == null) {
            if (other.nombre != null)
                return false;
        } else if (!nombre.equals(other.nombre))
            return false;
        if (descripcion == null) {
            if (other.descripcion != null)
                return false;
        } else if (!descripcion.equals(other.descripcion))
            return false;
        if (precio == null) {
            if (other.precio != null)
                return false;
        } else if (!precio.equals(other.precio))
            return false;
        if (cantidad != other.cantidad)
            return false;
        if (categoria == null) {
            if (other.categoria != null)
                return false;
        } else if (!categoria.equals(other.categoria))
            return false;
        if (imagenUrl == null) {
            if (other.imagenUrl != null)
                return false;
        } else if (!imagenUrl.equals(other.imagenUrl))
            return false;
        if (platos == null) {
            if (other.platos != null)
                return false;
        } else if (!platos.equals(other.platos))
            return false;
        return true;
    }



    
    

}

