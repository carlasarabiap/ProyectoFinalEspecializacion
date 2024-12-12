package ar.edu.centro8.desarrollo.proyecto.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ClienteDto {
    private Long id;
    private String nombre;
    private String email;
    private int telefono;
    private String direccion;
    private int edad;
    private String password;
    
}



