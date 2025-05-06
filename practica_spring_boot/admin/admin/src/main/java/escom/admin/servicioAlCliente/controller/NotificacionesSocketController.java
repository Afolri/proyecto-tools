package escom.admin.servicioAlCliente.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import escom.admin.servicioAlCliente.dto.ComentarioTicketResponseDTO;
import escom.admin.servicioAlCliente.dto.NotificacionResponseDTO;
import escom.admin.servicioAlCliente.entities.Comentario;
import escom.admin.servicioAlCliente.repositories.AgentesRespository;
import escom.admin.servicioAlCliente.repositories.TicketRepository;
import escom.admin.servicioAlCliente.services.NotificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class NotificacionesSocketController {
    AgentesRespository agentesRespository;
    private final SimpMessagingTemplate messagingTemplate;
    private Message<?> message;

    public NotificacionesSocketController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/notificacion/{usuario}")
    @SendTo("/topic/{usuario}")
    public NotificacionResponseDTO enviarNotificacion(@DestinationVariable String usuario,
                                                     NotificacionResponseDTO notificacionResponseDTO){
        return notificacionResponseDTO;
    }

    @MessageMapping("/comentario/general")
    @SendTo("/comentario-topic/general")
    public ComentarioTicketResponseDTO enviarComentario(ComentarioTicketResponseDTO comentario){
        return ComentarioTicketResponseDTO.builder()
                .numeroComentario(comentario.getNumeroComentario())
                .comentario(comentario.getComentario())
                .numeroUsuario(comentario.getNumeroUsuario())
                .numeroTicket(comentario.getNumeroTicket())
                .build();

    }
}
