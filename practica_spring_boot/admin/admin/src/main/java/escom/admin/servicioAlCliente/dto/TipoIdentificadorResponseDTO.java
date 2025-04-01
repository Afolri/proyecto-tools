package escom.admin.servicioAlCliente.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TipoIdentificadorResponseDTO {

    @JsonProperty("numero_identificador")
    private Long numeroIdentificador;

    @JsonProperty("nombre_identificador")
    private String nombreIdentificador;
}
