package escom.admin.servicioAlCliente.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ComentarioTicketResponseDTO {
    @JsonProperty ( "numero_usuario")
    Long numeroUsuario;
    @JsonProperty ("numero_ticket")
    Long numeroTicket;
    @JsonProperty("numero_comentario")
    Long numeroComentario;
    String comentario;
}
