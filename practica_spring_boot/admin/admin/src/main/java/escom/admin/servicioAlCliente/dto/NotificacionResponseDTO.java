package escom.admin.servicioAlCliente.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@JsonIgnoreProperties
public class NotificacionResponseDTO {
    @JsonProperty("numero_notificacion")
    Long numeroNotificacion;
    @JsonProperty("numero_ticket")
    Long numeroTicket;
    @JsonProperty("numero_usuario")
    Long usuarioAgenteAsignado;
    LocalDate fecha;
    LocalTime hora;
    @JsonProperty("estado_notificacion")
    Boolean estadoNotificacion;
    String mensaje;
    String estado;
    @JsonProperty("nombre_cliente")
    String nombreCliente;
}
