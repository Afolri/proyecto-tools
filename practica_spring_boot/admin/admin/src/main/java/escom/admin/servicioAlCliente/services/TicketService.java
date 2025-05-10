package escom.admin.servicioAlCliente.services;

import escom.admin.productos.model.Producto;
import escom.admin.servicioAlCliente.dto.DatosSocketDTOResponse;
import escom.admin.servicioAlCliente.dto.NotificacionResponseDTO;
import escom.admin.servicioAlCliente.dto.TicketRequestDTO;
import escom.admin.servicioAlCliente.dto.TicketResponseDTO;
import escom.admin.servicioAlCliente.entities.Cliente;
import escom.admin.servicioAlCliente.entities.ProductoTicket;
import escom.admin.servicioAlCliente.entities.Ticket;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface TicketService {

    void generarTickets (String asunto, String descripcion, String numeroSerieProducto, Long numeroCliente,
                         Long numeroAgente);
    void cambiarEstado (Long numeroTicket, String estado);
    void cerrarPedido(Long numeroTicket);
    void asignarAgente(Long idAgente);
    DatosSocketDTOResponse crearTicketConCliente(TicketRequestDTO requestDTO) throws Exception;
    List<TicketResponseDTO> buscarTickets( Long numeroUsuario, String estadoTickets );
    void actualizarTicket(Ticket ticket, Cliente cliente, ProductoTicket productoTicket);
    Ticket buscarPorIdentificador(String nombreIdentificador,  Long numeroTicket);
    List<TicketResponseDTO> obtenerTodosLosTickets();
}
