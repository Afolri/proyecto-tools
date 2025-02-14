package escom.admin.servicioAlCliente.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import escom.admin.servicioAlCliente.dto.TicketRequestDTO;
import escom.admin.servicioAlCliente.entities.Ticket;
import escom.admin.servicioAlCliente.services.TicketService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("/reporte-tickets")
public class ReporteTickets {
    @Autowired
    private TicketService ticketService;

    @PostMapping("/crear-ticket")
    public ResponseEntity<?> crearTicket(@RequestBody TicketRequestDTO ticketRequestDTO) {
        try {

            Map<String, String> respuesta = ticketService.crearTicketConCliente(ticketRequestDTO);
            System.out.println(ticketRequestDTO.toString());
            return (respuesta.get("error").equals("")) ? ResponseEntity.ok().body("") :
                    ResponseEntity.badRequest().body(respuesta);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al crear el ticket");
        }
    }

    @GetMapping("/buscar-tickets")
    public ResponseEntity<?> buscarTickets() {
        try {
            return ResponseEntity.ok().body(ticketService.buscarTickets());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al buscar los tickets");
        }
    }

}
