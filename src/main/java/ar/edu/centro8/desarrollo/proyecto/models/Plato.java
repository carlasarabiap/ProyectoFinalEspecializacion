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
@Table(name="plato")
@NoArgsConstructor
public class Plato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_plato", nullable = false)
    private Long id;

    @Column(name="cantidad", nullable = false)
    private int cantidad;

    
    //RELACION PLATO - MENU
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "id_menu", nullable = false)
    private Menu menu;

    //RELACION PLATO - PEDIDO
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "id_pedido", nullable = false)
    private Pedido pedido;

    

    public Plato(int cantidad, Menu menu, Pedido pedido) {
        this.cantidad = cantidad;
        this.menu = menu;
        this.pedido = pedido;
    }
    //AGREGADO 
    //PLATO-MENU
    public void setMenu(Menu menu) {
        this.menu = menu;
    } 
    //PEDIDO-PLATO
    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + cantidad;
        result = prime * result + ((menu == null) ? 0 : menu.hashCode());
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
        if (cantidad != other.cantidad)
            return false;
        if (menu == null) {
            if (other.menu != null)
                return false;
        } else if (!menu.equals(other.menu))
            return false;
        if (pedido == null) {
            if (other.pedido != null)
                return false;
        } else if (!pedido.equals(other.pedido))
            return false;
        return true;
    }

   
       

}