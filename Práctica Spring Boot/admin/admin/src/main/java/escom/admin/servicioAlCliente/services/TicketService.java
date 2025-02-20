package escom.admin.servicioAlCliente.services;

import escom.admin.productos.model.Producto;
import escom.admin.servicioAlCliente.dto.TicketRequestDTO;
import escom.admin.servicioAlCliente.entities.Cliente;
import escom.admin.servicioAlCliente.entities.ProductoTicket;
import escom.admin.servicioAlCliente.entities.Ticket;
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
    public Map<String,String> crearTicketConCliente(TicketRequestDTO requestDTO);
    List<Map<String,Object>> buscarTickets();
    void eliminarTicket(long numeroTicket);
    void actualizarTicket(Ticket ticket, Cliente cliente, ProductoTicket productoTicket);
}
