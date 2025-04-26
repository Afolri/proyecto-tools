package escom.admin.servicioAlCliente.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ComentarioTicketRequestDTO {
    @JsonProperty("numero_ticket")
    private Long numeroTicket;
    @JsonProperty("numero_usuario")
    private Long numeroUsuario;
    @JsonProperty("comentario")
    private String comentario;
}
