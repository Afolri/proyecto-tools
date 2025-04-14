package escom.admin.servicioAlCliente.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DatosSocketDTOResponse {
    private TicketResponseDTO ticketResponseDTO;
    private NotificacionResponseDTO notificacionResponseDTO;
}
