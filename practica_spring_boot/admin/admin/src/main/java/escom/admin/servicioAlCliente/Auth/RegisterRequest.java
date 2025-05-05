package escom.admin.servicioAlCliente.Auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    String correo;
    @JsonProperty("nombre_usuario")
    String nombreUsuario;
    String password;
    @JsonProperty("password_confirmar")
    String passwordConfirmar;
    String rol;
}
