package escom.admin.servicioAlCliente.services;

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
         || requestDTO.getAsunto().isEmpty() || requestDTO.getDescripcion().isEmpty() || requestDTO.getNumeroSerieModelo().isEmpty()
        || requestDTO.getNumeroCompraCot().isEmpty()){
            return Map.of("error", "Campos vacios");
        }

        productoTicket = productoTicketRepository.findByNumeroSerieModelo(requestDTO.getNumeroSerieModelo());
        cliente = clienteRepository.findByCorreo(requestDTO.getCorreo());

        /*Cuando se comprueba que el producto no existe entonces crea un nuevo usuario colocandole
        numero de serie del producto*/
        if (productoTicket.isPresent()) {
            ticket.setProductoTicket(productoTicket.get());
        } else {
            ProductoTicket producto = new ProductoTicket();
            producto.setNumeroSerieModelo(requestDTO.getNumeroSerieModelo().toUpperCase());
            producto.setNumeroCompraCot(requestDTO.getNumeroCompraCot().toUpperCase());
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

    @Override
    public void eliminarTicket(long numeroTicket) {
        ticketRepository.deleteById(numeroTicket);
    }

    @Override
    public void actualizarTicket(Ticket ticket, Cliente cliente, ProductoTicket productoTicket) {
        Optional<Ticket> ticketOptional = ticketRepository.findById(ticket.getNumeroTicket());
        Optional<Cliente> clienteOptional = clienteRepository.findById(cliente.getNumeroCliente());
        Optional<ProductoTicket> productoTicketOptional = productoTicketRepository.findById(productoTicket.getNumeroProducto());
        if(ticketOptional.isPresent() && clienteOptional.isPresent() && productoTicketOptional.isPresent()){
            Ticket t = ticketOptional.get();
            Cliente c = clienteOptional.get();
            ProductoTicket p = productoTicketOptional.get();
            t.setAsunto(ticket.getAsunto());
            t.setDescripcion(ticket.getDescripcion());
            c.setNombreCliente(cliente.getNombreCliente());
            c.setCorreo(cliente.getCorreo());
            c.setTelefono(cliente.getTelefono());
            p.setNumeroSerieModelo(productoTicket.getNumeroSerieModelo());
            p.setNumeroCompraCot(productoTicket.getNumeroCompraCot());

            ticketRepository.save(t);
            clienteRepository.save(c);
            productoTicketRepository.save(p);
        }

    }
}
