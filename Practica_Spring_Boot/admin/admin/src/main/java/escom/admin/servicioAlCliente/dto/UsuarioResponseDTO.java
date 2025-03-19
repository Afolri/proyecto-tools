package escom.admin.servicioAlCliente.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import escom.admin.servicioAlCliente.entities.Rol;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioResponseDTO {
    @JsonProperty("numero_usuario")
    private Long numeroUsuario;
    private String nombre;
    private String correo;
    private Rol rol;



}
