package escom.admin.servicioAlCliente.Auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Como espera el controller los request y response
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    String correo;
    String password;
}
