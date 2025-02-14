package escom.admin.servicioAlCliente.services;

import org.springframework.stereotype.Service;

@Service
public interface ProductoTicketService {
    void crearProducto(String numeroSerieProducto);
}
