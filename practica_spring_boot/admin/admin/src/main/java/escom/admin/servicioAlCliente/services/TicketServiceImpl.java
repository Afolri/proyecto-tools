package escom.admin.servicioAlCliente.services;

import escom.admin.servicioAlCliente.dto.TicketRequestDTO;
import escom.admin.servicioAlCliente.dto.TicketResponseDTO;
import escom.admin.servicioAlCliente.entities.*;
import escom.admin.servicioAlCliente.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static escom.admin.servicioAlCliente.utils.UtilidadesDeValidacion.*;

@Service
public class TicketServiceImpl implements TicketService {
    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    ClienteRepository clienteRepository;
    @Autowired
    ProductoTicketRepository productoTicketRepository;

    @Autowired
    NotificacionService notificacionService;

    @Autowired
    ProductoTicketService productoTicketService;

    @Autowired
    AgentesRespository agentesRespository;

    @Autowired
    UsuarioRepository usuarioRepository;

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
        ticketRepository.cambiarEstado(numeroTicket, estado);
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
        Cliente cliente;
        ProductoTicket producto;

        //Busca un ticket por el nuemero de compra

        Ticket ticket= ticketRepository
                .findByCliente_CorreoAndProductoTicket_NumeroCompraCot(requestDTO.getCorreo(),requestDTO.getNumeroCompraCot())
                .orElse(new Ticket());

        if(!validarCorreo(requestDTO.getCorreo())) {
            return Map.of("error", "Correo invalido");
        }
        if(!validarTelefono(requestDTO.getTelefono())) {
            return Map.of("error", "Telefono invalido");
        }
        if(!validarNombre(requestDTO.getNombreCliente())){
            return Map.of("error", "Nombre invalido");
        }
        if(ticketDtoVacio(requestDTO)){
            return Map.of("error", "Campos vacios");
        }

        /*Cuando se comprueba que el producto no existe entonces crea un nuevo producto colocandole
        numero de identificador*/
        producto = productoTicketService.crearProducto(requestDTO.getNumeroCompraCot(),
                requestDTO.getCodigo().toUpperCase(), requestDTO.getNumeroIdentificador());
        ticket.setProductoTicket(producto);

        /*
        Cuando se comprueba que el cliente no existe entonces se guarda la entidad en la entidad ticket en su atributo
        de cliente
         */
        cliente = clienteRepository.findByCorreo(requestDTO.getCorreo()).orElseGet(() ->clienteRepository.save(
                Cliente.builder()
                .nombreCliente(requestDTO.getNombreCliente())
                .correo(requestDTO.getCorreo().toLowerCase())
                .telefono(requestDTO.getTelefono())
                .build()));
                /*
                .orElseGet());
                 */

        //guarda el asunto, descripcion y la fecha en el ticket
        ticket = ticketRepository.save(Ticket.builder()
                .numeroTicket(ticket.getNumeroTicket())
                .asunto(requestDTO.getAsunto())
                .descripcion(requestDTO.getDescripcion())
                .fecha( LocalDate.now() )
                .hora( LocalTime.parse(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"))))
                .agente( (ticket.getAgente()==null)?
                        agentesRespository.findById(ticketRepository.siguienteAgente()).orElse(null):
                        ticket.getAgente())
                .estado(ticket.getEstado())
                .productoTicket(producto)
                .cliente(cliente)
                .build());
        notificacionService.crearNotificacion(ticket, "Se ha creado un nuevo ticket");
        return Map.of("error", "");
    }

    boolean ticketDtoVacio(TicketRequestDTO tr){
        return (tr.getCorreo().isEmpty() || tr.getTelefono().isEmpty() || tr.getNombreCliente().isEmpty()
                || tr.getAsunto().isEmpty() || tr.getDescripcion().isEmpty() || tr.getCodigo().isEmpty()
                || tr.getNumeroCompraCot().isEmpty());

    }
    @Override
    public List<TicketResponseDTO> buscarTickets(Long numeroUsuario) {
        try {
            return ticketRepository.buscarTicketDTO(numeroUsuario);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return List.of();
        }
    }
    List<Map<String,String>> interleave(List<String> list1, List<String> list2) {
        List<Map<String,String>> result = new ArrayList<>();
        for (int i = 0; i < list1.size(); i++) {
            Map<String,String> map = new HashMap<>();
            map.put(list1.get(i),list2.get(i) );
            result.add(map);
        }
        return result;
    }



    @Override
    public void actualizarTicket(Ticket ticket, Cliente cliente, ProductoTicket productoTicket) {
        Optional<Ticket> ticketOptional = ticketRepository.findById(ticket.getNumeroTicket());
        Optional<Cliente> clienteOptional = clienteRepository.findById(cliente.getNumeroCliente());
        Optional<ProductoTicket> productoTicketOptional = productoTicketRepository.
                findById(productoTicket.getNumeroProducto());

        if(ticketOptional.isPresent() && clienteOptional.isPresent() && productoTicketOptional.isPresent()){
            Ticket t = ticketOptional.get();
            Cliente c = clienteOptional.get();
            ProductoTicket p = productoTicketOptional.get();
            t.setAsunto(ticket.getAsunto());
            t.setDescripcion(ticket.getDescripcion());
            c.setNombreCliente(cliente.getNombreCliente());
            c.setCorreo(cliente.getCorreo());
            c.setTelefono(cliente.getTelefono());
            p.setNumeroCompraCot(productoTicket.getNumeroCompraCot());

            ticketRepository.save(t);
            clienteRepository.save(c);
            productoTicketRepository.save(p);
        }

    }

    @Override
    public Ticket buscarPorIdentificador(String nombreIdentificador, Long numeroTicket) {
        Optional<Ticket> ticket = ticketRepository.buscarPorIdentificador(nombreIdentificador, numeroTicket);
        return ticket.orElseGet(Ticket::new);
    }
}
