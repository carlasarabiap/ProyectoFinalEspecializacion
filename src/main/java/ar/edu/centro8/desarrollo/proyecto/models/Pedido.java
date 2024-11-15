package ar.edu.centro8.desarrollo.proyecto.models;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name="pedidos")
@NoArgsConstructor
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_pedido", nullable = false)
    private Long id;

    @Column(name="fecha", nullable = false)
    private LocalDateTime fecha;

    @PrePersist
    protected void onCreate() {
        this.fecha = LocalDateTime.now();
    }

    @Column(name="cantidad", nullable = false)
    private int cantidad;

    @Column(name="estado", nullable = false)
    private String estado;

    @Column(name="notas", nullable = true)
    private String notas;

    //RELACION PEDIDO - CLIENTE
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

    //RELACION PEDIDO - FACTURA (BIDIRECCIONAL FK - PK)
    @OneToOne(mappedBy = "pedido", cascade = CascadeType.ALL)
    private Factura factura;


    //AGREGADO 
    //PEDIDO-CLIENTE
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((fecha == null) ? 0 : fecha.hashCode());
        result = prime * result + cantidad;
        result = prime * result + ((estado == null) ? 0 : estado.hashCode());
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
        if (cantidad != other.cantidad)
            return false;
        if (estado == null) {
            if (other.estado != null)
                return false;
        } else if (!estado.equals(other.estado))
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