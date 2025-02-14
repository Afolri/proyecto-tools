package escom.admin.servicioAlCliente.services;

import escom.admin.productos.model.Producto;
import escom.admin.servicioAlCliente.dto.TicketRequestDTO;
import escom.admin.servicioAlCliente.entities.Cliente;
import escom.admin.servicioAlCliente.entities.ProductoTicket;
import escom.admin.servicioAlCliente.entities.Ticket;
import escom.admin.servicioAlCliente.repositories.ClienteRepository;
import escom.admin.servicioAlCliente.repositories.ProductoTicketRepository;
import escom.admin.servicioAlCliente.repositories.TicketRepository;
import escom.admin.servicioAlCliente.utils.UtilidadesDeValidacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class TicketServiceImpl implements TicketService {
    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    ClienteRepository clienteRepository;
    @Autowired
    ProductoTicketRepository productoTicketRepository;
    @Override
    public void generarTickets(String asunto, String descripcion, String numeroSerieProducto, Long numeroCliente, Long numeroAgente) {
        Ticket ticket = new Ticket();
        ticket.setAsunto(asunto);
        ticket.setDescripcion(descripcion);
        ticket.setNumeroTicket(numeroCliente);
        ticketRepository.save(ticket);
    }


    @Override
    public void cambiarEstado(Long numeroTicket, String estado) {
        Optional<Ticket> ticket = ticketRepository.findById(numeroTicket);
        if(ticket.isPresent()){
            Ticket t = (Ticket) ticket.get();
            t.setEstado(estado);
            ticketRepository.save(t);
        }

    }

    @Override
    public void cerrarPedido(Long numeroTicket) {
        Optional<Ticket> ticket = ticketRepository.findById(numeroTicket);
        if(ticket.isPresent()){
            Ticket t = (Ticket) ticket.get();
            t.setEstado("CERRADO");
            ticketRepository.save(t);
        }
    }

    @Override
    public void asignarAgente(Long idAgente) {

    }

    @Override
    public Map<String,String> crearTicketConCliente(TicketRequestDTO requestDTO) {
        Optional <ProductoTicket>  productoTicket;
        Optional <Cliente> cliente;
        Ticket ticket = new Ticket();

        if(!UtilidadesDeValidacion.validarCorreo(requestDTO.getCorreo())) {
            return Map.of("error", "Correo invalido");
        }
        if(!UtilidadesDeValidacion.validarTelefono(requestDTO.getTelefono())) {
            return Map.of("error", "Telefono invalido");
        }
        if(!UtilidadesDeValidacion.validarNombre(requestDTO.getNombreCliente())){
            return Map.of("error", "Nombre invalido");
        }
        if(requestDTO.getCorreo().isEmpty() || requestDTO.getTelefono().isEmpty() || requestDTO.getNombreCliente().isEmpty()
         || requestDTO.getAsunto().isEmpty() || requestDTO.getDescripcion().isEmpty() || requestDTO.getNumeroDeSerie().isEmpty()
        || requestDTO.getNumeroCompraCot().isEmpty()){
            return Map.of("error", "Campos vacios");
        }

        productoTicket = productoTicketRepository.findByNumeroSerieProducto(requestDTO.getNumeroDeSerie());
        cliente = clienteRepository.findByCorreo(requestDTO.getCorreo());

        /*Cuando se comprueba que el producto no existe entonces crea un nuevo usuario colocandole
        numero de serie del producto*/
        if (productoTicket.isPresent()) {
            ticket.setProductoTicket(productoTicket.get());
        } else {
            ProductoTicket producto = new ProductoTicket();
            producto.setNumeroSerieProducto(requestDTO.getNumeroDeSerie());
            producto.setNumeroCompraCot(requestDTO.getNumeroCompraCot());
            productoTicketRepository.save(producto);
            ticket.setProductoTicket(producto);
        }

        /*
        Cuando se comprueba que el cliente no existe entonces se guarda la entidad en la entidad ticket en su atributo
        de cliente
         */
        if (cliente.isPresent()) {
            ticket.setCliente(cliente.get());
        } else {
            Cliente c = new Cliente();
            c.setNombreCliente(requestDTO.getNombreCliente());
            c.setCorreo(requestDTO.getCorreo());
            c.setTelefono(requestDTO.getTelefono());
            clienteRepository.save(c);
            ticket.setCliente(c);
        }

        ticket.setAsunto(requestDTO.getAsunto());
        ticket.setDescripcion(requestDTO.getDescripcion());

        ticketRepository.save(ticket);
        return Map.of("error", "");

    }

    @Override
    public List<Map<String, Object>> buscarTickets() {
        return ticketRepository.buscarTickets();
    }
}
