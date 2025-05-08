package escom.admin.servicioAlCliente.services;

import escom.admin.servicioAlCliente.dto.ComentarioTicketResponseDTO;
import escom.admin.servicioAlCliente.entities.Comentario;
import escom.admin.servicioAlCliente.entities.ComentarioTicket;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ComentarioTicketService {
    ComentarioTicketResponseDTO comentarTicket(Long numeroTicket, Long numeroAgente, String comentario) throws Exception;
    List<ComentarioTicketResponseDTO> buscarComentariosPorTicket(Long numeroTicket);
}
