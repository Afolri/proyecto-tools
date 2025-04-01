package escom.admin.servicioAlCliente.services;

import escom.admin.servicioAlCliente.entities.ProductoTicket;
import org.springframework.stereotype.Service;

@Service
public interface ProductoTicketService {
    ProductoTicket crearProducto(String numeroCompraCot, String codigo, Long numeroIdentificador);
}
