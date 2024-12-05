package ar.edu.centro8.desarrollo.proyecto.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "pedidos")
@NoArgsConstructor
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedido", nullable = false)
    private Long id;

    @Column(name = "fecha", nullable = false)
    private LocalDateTime fecha;

    @PrePersist
    protected void onCreate() {
        this.fecha = LocalDateTime.now();
    }

    @Column(name = "notas", nullable = true)
    private String notas;

    // RELACIÓN PEDIDO - CLIENTE
    @ManyToOne
    // @JsonBackReference // Evitar referencia cíclica al serializar
    @JoinColumn(name = "id_cliente", referencedColumnName = "id_cliente", nullable = false)
    private Cliente cliente;

    // RELACIÓN PEDIDO - FACTURA (Bidireccional FK - PK)
    @OneToOne(mappedBy = "pedido", cascade = CascadeType.ALL)
    @JsonManagedReference // Lado gestionado de la relación
    private Factura factura;

    // RELACIÓN PEDIDO - PLATO
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Plato> platos = new ArrayList<>();

    // Constructor con parámetros
    public Pedido(LocalDateTime fecha, String notas, Cliente cliente, List<Plato> platos) {
        this.fecha = fecha;
        this.notas = notas;
        this.cliente = cliente;
        this.platos = platos;
    }

    // Métodos auxiliares para gestionar platos
    public void agregarPlato(Plato plato) {
        this.platos.add(plato);
        plato.setPedido(this);
    }

    public void removerPlato(Plato plato) {
        this.platos.remove(plato);
        plato.setPedido(null);
    }

    // Agregado: Asociar cliente
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    // Métodos equals y hashCode generados automáticamente
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((fecha == null) ? 0 : fecha.hashCode());
        result = prime * result + ((notas == null) ? 0 : notas.hashCode());
        result = prime * result + ((cliente == null) ? 0 : cliente.hashCode());
        result = prime * result + ((factura == null) ? 0 : factura.hashCode());
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
        Pedido other = (Pedido) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (fecha == null) {
            if (other.fecha != null)
                return false;
        } else if (!fecha.equals(other.fecha))
            return false;
        if (notas == null) {
            if (other.notas != null)
                return false;
        } else if (!notas.equals(other.notas))
            return false;
        if (cliente == null) {
            if (other.cliente != null)
                return false;
        } else if (!cliente.equals(other.cliente))
            return false;
        if (factura == null) {
            if (other.factura != null)
                return false;
        } else if (!factura.equals(other.factura))
            return false;
        return true;
    }
}
