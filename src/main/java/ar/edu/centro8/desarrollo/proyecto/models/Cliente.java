package ar.edu.centro8.desarrollo.proyecto.models;

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
@Table(name="clientes")
@NoArgsConstructor
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente", nullable = false)
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

    //RELACION CLIENTE-PEDIDO
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    // @JsonManagedReference
    @JsonIgnore
    private List<Pedido> pedidos;

    public Cliente(String nombre, String email, int telefono, String direccion, int edad, String password, List<Pedido> pedidos) {
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.direccion = direccion;
        this.edad = edad;
        this.password = password;
        this.pedidos = pedidos;
    }
    
    // Constructor para inicializar un Cliente con solo el ID
    public Cliente(Long id) {
        this.id = id;
    }

    //AGREGADO
    public Pedido agregarPedido(Pedido pedido) {
        this.pedidos.add(pedido);
        return pedido;
    }
       
}