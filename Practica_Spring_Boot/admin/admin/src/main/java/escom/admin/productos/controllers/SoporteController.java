package escom.admin.productos.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import escom.admin.servicioAlCliente.entities.Ticket;
import escom.admin.servicioAlCliente.services.TicketService;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/soporte")
public class SoporteController {
    private final TicketService ticketService;

    public SoporteController(TicketService ticketService) {
        this.ticketService = ticketService;
    }




}
