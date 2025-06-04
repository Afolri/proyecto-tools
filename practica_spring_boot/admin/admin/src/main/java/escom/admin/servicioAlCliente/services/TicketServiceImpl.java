package escom.admin.servicioAlCliente.services;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import escom.admin.servicioAlCliente.Projection.TicketProjection;
import escom.admin.servicioAlCliente.dto.DatosSocketDTOResponse;
import escom.admin.servicioAlCliente.dto.NotificacionResponseDTO;
import escom.admin.servicioAlCliente.dto.TicketRequestDTO;
import escom.admin.servicioAlCliente.dto.TicketResponseDTO;
import escom.admin.servicioAlCliente.entities.*;
        import escom.admin.servicioAlCliente.repositories.*;
        import org.json.JSONObject;
import org.json.JSONString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
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
    public DatosSocketDTOResponse crearTicketConCliente(TicketRequestDTO requestDTO) throws Exception {
        Cliente cliente;
        ProductoTicket producto;
        ObjectMapper objectMapper = new ObjectMapper();
        JSONObject jsonObject = new JSONObject(requestDTO);
        Ticket ticket = new Ticket();
        //Busca un ticket por el nuemero de compra

        Optional<TicketProjection> ticketProjection =
                ticketRepository.buscarTicketExistente(requestDTO.getCorreo(), requestDTO.getNumeroCompraCot());
        if(ticketProjection.isPresent()) {
            ticket = new Ticket(
                    ticketProjection.get().getNumeroTicket(),
                    ticketProjection.get().getAsunto(),
                    ticketProjection.get().getDescripcion(),
                    ticketProjection.get().getEstado(),
                    ticketProjection.get().getFecha(),
                    ticketProjection.get().getHora()
            );
        }else{
            ticket = new Ticket();
        }

        if(!validarCorreo(requestDTO.getCorreo())) {
            throw new Exception("El correo no cumple el patrón");
        }

        if(!validarTelefono(requestDTO.getTelefono())) {
            throw new Exception("El teléfono no cumple el patrón");
        }
        if(!validarNombre(requestDTO.getNombreCliente())){
            throw new Exception("El nombre no cumple el patrón");
        }
        if(ticketDtoVacio(requestDTO)){
            throw new Exception("Hay un campo vacio");
        }
        /*
        *Cuando se comprueba que el producto no existe entonces crea un nuevo producto colocandole
        numero de identificador
        */

        producto = productoTicketService.crearProducto(requestDTO.getNumeroCompraCot(),
                requestDTO.getCodigo().toUpperCase(), requestDTO.getNumeroIdentificador());
        ticket.setNumeroProducto(producto.getNumeroProducto());

        /*
         *Cuando se comprueba que el cliente no existe entonces se guarda la entidad en la entidad ticket en su atributo
         *de cliente
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

        //obtener agente
        Agente agente = (ticket.getNumeroAgente()==null)?
                agentesRespository.findById(ticketRepository.siguienteAgente()).orElse(null):
                agentesRespository.findById(ticket.getNumeroAgente()).orElseThrow();
        //guarda el asunto, descripcion y la fecha en el ticket
        ticket = ticketRepository.save(Ticket.builder()
                .numeroTicket(ticket.getNumeroTicket())
                .asunto(requestDTO.getAsunto())
                .descripcion(requestDTO.getDescripcion())
                .fecha( LocalDate.now() )
                .hora( LocalTime.parse(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"))))
                .numeroAgente(agente.getNumeroAgente())
                .estado(ticket.getEstado())
                .numeroProducto(producto.getNumeroProducto())
                .numeroCliente(cliente.getNumeroCliente())
                .build());
        NotificacionResponseDTO notiDTO = notificacionService.crearNotificacion(agente.getNumeroUsuario(), "Se ha creado un nuevo ticket");
        TicketResponseDTO ticketDTO = TicketResponseDTO.builder()
                .numeroTicket(ticket.getNumeroTicket())
                .numeroProducto(ticket.getNumeroProducto())
                .tipoCodigo(productoTicketRepository.obtenerIdentificadores(producto.getNumeroProducto()))
                .numeroCompraCot(producto.getNumeroCompraCot())
                .numeroAgente(ticket.getNumeroAgente())
                .asunto(ticket.getAsunto())
                .numeroCliente(ticket.getNumeroCliente())
                .telefono(cliente.getTelefono())
                .correo(cliente.getCorreo())
                .descripcion(ticket.getDescripcion())
                .estado(ticket.getEstado())
                .fecha(ticket.getFecha())
                .hora(ticket.getHora())
                .build();
        return DatosSocketDTOResponse.builder()
                .notificacionResponseDTO(notiDTO)
                .ticketResponseDTO(ticketDTO)
                .build();
    }

    boolean ticketDtoVacio(TicketRequestDTO tr){
        return (tr.getCorreo().isEmpty() || tr.getTelefono().isEmpty() || tr.getNombreCliente().isEmpty()
                || tr.getAsunto().isEmpty() || tr.getDescripcion().isEmpty() || tr.getCodigo().isEmpty()
                || tr.getNumeroCompraCot().isEmpty());

    }
    @Override
    public List<TicketResponseDTO> buscarTickets(Long numeroUsuario, String estadoTickets) {
        try {
            Usuario usuarioTemp = usuarioRepository.findById(numeroUsuario).orElseThrow();
            if(usuarioTemp.getRol() == Rol.ADMIN){
                return ticketRepository.obtenerTodosLosTicketsPorEstado(estadoTickets).stream()
                        .map(ticket ->{
                            ObjectMapper objectMapper = new ObjectMapper();
                            objectMapper.registerModule(new JavaTimeModule());

                            try {
                                return objectMapper.readValue(new JSONObject(ticket).toString(), TicketResponseDTO.class);
                            } catch (JsonProcessingException e) {
                                throw new RuntimeException(e);
                            }
                        })
                        .toList();
            }
            return ticketRepository.buscarTicketsDTO(numeroUsuario, estadoTickets).stream()
                    .map(ticket ->{
                        ObjectMapper objectMapper = new ObjectMapper();
                        objectMapper.registerModule(new JavaTimeModule());

                        try {
                            return objectMapper.readValue(new JSONObject(ticket).toString(), TicketResponseDTO.class);
                        } catch (JsonProcessingException e) {
                            throw new RuntimeException(e);
                        }
                    })
                    .toList();
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

    @Override
    public List<TicketResponseDTO> obtenerTodosLosTickets() {
        return ticketRepository.obtenerTodosLosTickets().stream()
                .map(ticket -> {
                    ObjectMapper objectMapper = new ObjectMapper()
                            .registerModule(new JavaTimeModule());
                    try {
                        return objectMapper.readValue(new JSONObject(ticket).toString(), TicketResponseDTO.class);
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }


                }).toList();
    }
}
