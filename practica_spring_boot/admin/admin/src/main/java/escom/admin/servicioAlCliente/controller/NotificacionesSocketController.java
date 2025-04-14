package escom.admin.servicioAlCliente.controller;

import escom.admin.servicioAlCliente.dto.NotificacionResponseDTO;
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

    private final SimpMessagingTemplate messagingTemplate;
    private Message<?> message;

    public NotificacionesSocketController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/notificacion/{agente}")
    @SendTo("/topic/{agente}")
    public NotificacionResponseDTO enviarNotificacion(@DestinationVariable String agente,
                                                     NotificacionResponseDTO notificacionResponseDTO){
        return notificacionResponseDTO;
    }
}
