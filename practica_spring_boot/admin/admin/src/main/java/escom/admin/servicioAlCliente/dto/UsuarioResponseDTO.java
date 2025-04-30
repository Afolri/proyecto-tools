package escom.admin.servicioAlCliente.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import escom.admin.servicioAlCliente.entities.Rol;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class UsuarioResponseDTO {
    @JsonProperty("numero_usuario")
    private Long numeroUsuario;
    @JsonProperty("nombre_usuario")
    private String nombreUsuario;
    private String correo;
    private Rol rol;



}
