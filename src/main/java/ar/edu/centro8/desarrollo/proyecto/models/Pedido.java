package ar.edu.centro8.desarrollo.proyecto.models;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

    public Pedido(int cantidad, String estado, String notas) {
        this.cantidad = cantidad;
        this.estado = estado;
        this.notas = notas;
    }       
}