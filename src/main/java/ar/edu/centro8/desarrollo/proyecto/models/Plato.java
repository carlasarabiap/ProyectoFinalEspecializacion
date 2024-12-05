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
    // @JsonBackReference
    @JoinColumn(name = "id_menu", nullable = false)
    private Menu menu;

    //RELACION PLATO - PEDIDO
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "id_pedido", referencedColumnName = "id_pedido", nullable = false)
    private Pedido pedido;

    // public Plato(int cantidad, Menu menu, Pedido pedido) {
    //     this.cantidad = cantidad;
    //     this.menu = menu;
    //     this.pedido = pedido;
    // }

    public Plato(int cantidad, Menu menu) {
        this.cantidad = cantidad;
        this.menu = menu;
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
    
}
