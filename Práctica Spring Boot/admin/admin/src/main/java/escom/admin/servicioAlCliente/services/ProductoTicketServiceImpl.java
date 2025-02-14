package escom.admin.servicioAlCliente.services;

import escom.admin.productos.repositories.ProductoRepository;
import escom.admin.servicioAlCliente.entities.ProductoTicket;
import escom.admin.servicioAlCliente.repositories.ProductoTicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoTicketServiceImpl implements ProductoTicketService{
    @Autowired
    ProductoTicketRepository productoTicketRepository;
    @Override
    public void crearProducto(String numeroSerieProducto) {
        ProductoTicket productoTicket = new ProductoTicket();
        productoTicket.setNumeroSerieProducto(numeroSerieProducto);
        productoTicketRepository.save(productoTicket);
    }
}
