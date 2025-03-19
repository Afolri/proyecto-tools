package escom.admin.servicioAlCliente.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

@lombok.Data
@lombok.AllArgsConstructor
public class NotificacionResponseDTO {
    @JsonProperty("numero_notificacion")
    Long numeroNotificacion;
    @JsonProperty("numero_ticket")
    Long numeroTikcet;
    LocalDateTime fecha;
    @JsonProperty("estado_notificacion")
    Boolean estadoNotificacion;
    String mensaje;
    String estado;
    @JsonProperty("nombre_cliente")
    String nombreCliente;
}
