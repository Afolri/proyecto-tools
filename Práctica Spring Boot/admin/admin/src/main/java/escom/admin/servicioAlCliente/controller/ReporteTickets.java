package escom.admin.servicioAlCliente.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import escom.admin.servicioAlCliente.dto.TicketRequestDTO;
import escom.admin.servicioAlCliente.entities.Cliente;
import escom.admin.servicioAlCliente.entities.ProductoTicket;
import escom.admin.servicioAlCliente.entities.Ticket;
import escom.admin.servicioAlCliente.services.TicketService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    @DeleteMapping("/eliminar-ticket")
    public ResponseEntity<?> eliminarTicket(@RequestParam Long numeroTicket) {
        try {
            ticketService.eliminarTicket(numeroTicket);
            return ResponseEntity.ok().body("Ticket eliminado");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al eliminar el ticket");
        }
    }
    @PutMapping("/actualizar-ticket")
    public ResponseEntity<?> actualizarTicket(@RequestBody String json) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JSONObject jsonObject = new JSONObject(json);
            Ticket ticket = objectMapper.readValue(jsonObject.get("ticket").toString(), Ticket.class);
            Cliente cliente = objectMapper.readValue(jsonObject.get("cliente").toString(), Cliente.class);
            ProductoTicket productoTicket = objectMapper.readValue(jsonObject.get("productoticket").toString(), ProductoTicket.class);
            ticketService.actualizarTicket(ticket, cliente, productoTicket);
            return ResponseEntity.ok().body("Ticket actualizado");
        } catch (JsonProcessingException e) {
            return ResponseEntity.badRequest().body("Error al actualizar el ticket");
        }
    }
}
