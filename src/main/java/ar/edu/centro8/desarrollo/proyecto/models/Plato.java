package ar.edu.centro8.desarrollo.proyecto.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

    //RELACION PEDIDO - PLATO
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "id_pedido")
    private Pedido pedido;

    public Plato(String nombre, String descripcion, Double precio, int cantidad, String categoria, String imagenUrl, Pedido pedido) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.cantidad = cantidad;
        this.categoria = categoria;
        this.imagenUrl = imagenUrl;
        this.pedido = pedido;
    }

    //AGREGADO
    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
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
        result = prime * result + ((pedido == null) ? 0 : pedido.hashCode());
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
        Plato other = (Plato) obj;
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
        if (pedido == null) {
            if (other.pedido != null)
                return false;
        } else if (!pedido.equals(other.pedido))
            return false;
        return true;
    }
    
    
}
