package escom.admin.servicioAlCliente.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import escom.admin.servicioAlCliente.dto.DatosSocketDTOResponse;
import escom.admin.servicioAlCliente.dto.NotificacionResponseDTO;
import escom.admin.servicioAlCliente.dto.TicketRequestDTO;
import escom.admin.servicioAlCliente.dto.TicketResponseDTO;
import escom.admin.servicioAlCliente.entities.Cliente;
import escom.admin.servicioAlCliente.entities.ProductoTicket;
import escom.admin.servicioAlCliente.entities.Ticket;
import escom.admin.servicioAlCliente.entities.TipoIdentificador;
import escom.admin.servicioAlCliente.services.NotificacionService;
import escom.admin.servicioAlCliente.services.TicketService;
import escom.admin.servicioAlCliente.services.TipoIdentificadorService;
import escom.admin.servicioAlCliente.services.UsuarioService;
import jdk.jfr.Unsigned;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Map;

@Controller
@RequestMapping("/reporte-tickets")
public class ReporteTickets {
    @Autowired
    private TicketService ticketService;
    @Autowired
    private NotificacionService notificacionService;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private TipoIdentificadorService tipoIdentificadorService;

    @PostMapping("/crear-ticket")
    public ResponseEntity<?> crearTicket(@RequestBody TicketRequestDTO ticketRequestDTO) {
        try {
            DatosSocketDTOResponse datosSocket= ticketService.crearTicketConCliente(ticketRequestDTO);
            return ResponseEntity.ok(datosSocket);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al crear el ticket");
        }
    }

    @GetMapping("/buscar-tickets")
    public ResponseEntity<?> buscarTickets(@RequestParam Long numeroUsuario) {
        try {
            return ResponseEntity.ok().body(ticketService.buscarTickets(numeroUsuario));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al buscar los tickets");
        }
    }
    @PutMapping("/cerrar-ticket")
    public ResponseEntity<?> cerratTicket(@RequestParam Long numeroTicket) {
        try {
            ticketService.cambiarEstado(numeroTicket, "CERRADO");
            return ResponseEntity.ok().body("Ticket cerrado");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al cerrar el ticket");
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

    @GetMapping("/obtenerNotificaciones")
    public ResponseEntity<?> obtenerNotificaciones(@RequestParam Long numeroUsuario) {
        try {
            return ResponseEntity.ok().body(notificacionService.verNotificaciones(numeroUsuario));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al obtener las notificaciones"+ e.getMessage());
        }
    }

    @GetMapping("/obtener-tickets-identificador")
    public ResponseEntity<?> obtenerTicketsIdentificador(@RequestParam String nombreIdentificador,
                                                         @RequestParam Long numeroTicket) {
        try {
            return ResponseEntity.ok().body(ticketService.buscarPorIdentificador(nombreIdentificador, numeroTicket));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al obtener los tickets");
        }
    }

    /*Metodo que sirve para poder obtener las credenciales del suuario*/
    @GetMapping("/obtener-credenciales")
    public ResponseEntity<?> obtenerCredenciales(@RequestHeader HttpHeaders httpHeaders){
        return ResponseEntity.ok().body(usuarioService.getUsuarioLoggeado(httpHeaders));
    }

    @PutMapping("/abrir-notificacion")
    public ResponseEntity<?> abrirNotificacion (@RequestParam ("numero-notificacion")Long numeroNotificacion){
        notificacionService.abrirNotificacion(numeroNotificacion);
        return ResponseEntity.ok().body("Marcada como leida");
    }

    @GetMapping("/obtener-identificadores")
    public ResponseEntity<?> obtenerIdentificadores(){
        return ResponseEntity.ok().body(tipoIdentificadorService.obtenerIdentificadores());
    }
    @GetMapping("/notificaciones-pendientes")
    public ResponseEntity<?> notificacionesPendientes(){
        return ResponseEntity.ok().body(notificacionService.notificacionesPendientes());
    }


}
