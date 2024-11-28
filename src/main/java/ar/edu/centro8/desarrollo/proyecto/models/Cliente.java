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
    @JsonManagedReference
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + telefono;
        result = prime * result + ((direccion == null) ? 0 : direccion.hashCode());
        result = prime * result + edad;
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        result = prime * result + ((pedidos == null) ? 0 : pedidos.hashCode());
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
        Cliente other = (Cliente) obj;
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
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        if (telefono != other.telefono)
            return false;
        if (direccion == null) {
            if (other.direccion != null)
                return false;
        } else if (!direccion.equals(other.direccion))
            return false;
        if (edad != other.edad)
            return false;
        if (password == null) {
            if (other.password != null)
                return false;
        } else if (!password.equals(other.password))
            return false;
        if (pedidos == null) {
            if (other.pedidos != null)
                return false;
        } else if (!pedidos.equals(other.pedidos))
            return false;
        return true;
    }
        
}