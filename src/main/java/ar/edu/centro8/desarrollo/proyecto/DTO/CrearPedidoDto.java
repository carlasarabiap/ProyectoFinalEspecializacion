package ar.edu.centro8.desarrollo.proyecto.DTO;

import java.util.Map;

public class CrearPedidoDto {
    private String notas;
    private Map<Long, Integer> menuIdsConCantidades; // ID del menú -> Cantidad

    // Getters y Setters
    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public Map<Long, Integer> getMenuIdsConCantidades() {
        return menuIdsConCantidades;
    }

    public void setMenuIdsConCantidades(Map<Long, Integer> menuIdsConCantidades) {
        this.menuIdsConCantidades = menuIdsConCantidades;
    }
}
