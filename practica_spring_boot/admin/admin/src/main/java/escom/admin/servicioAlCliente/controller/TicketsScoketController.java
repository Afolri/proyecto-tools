package escom.admin.servicioAlCliente.controller;

import escom.admin.servicioAlCliente.dto.TicketResponseDTO;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class TicketsScoketController {

    @MessageMapping("/ticket/{agente}")
    @SendTo("/topic/ticket/{agente}")
    public TicketResponseDTO enviarTicket(@DestinationVariable String agente, TicketResponseDTO ticketResponseDTO){
        return ticketResponseDTO;
    }
}
