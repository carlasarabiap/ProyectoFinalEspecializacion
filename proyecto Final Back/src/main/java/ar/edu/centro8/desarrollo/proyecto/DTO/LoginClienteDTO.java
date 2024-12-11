package ar.edu.centro8.desarrollo.proyecto.DTO;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginClienteDTO {

    private String email;
    private String password;


}