package escom.admin.servicioAlCliente.services;

import escom.admin.servicioAlCliente.dto.NotificacionResponseDTO;
import escom.admin.servicioAlCliente.entities.Notificacion;
import escom.admin.servicioAlCliente.entities.Ticket;
import escom.admin.servicioAlCliente.entities.Usuario;
import escom.admin.servicioAlCliente.repositories.NotificacionRepository;
import escom.admin.servicioAlCliente.repositories.TicketRepository;
import escom.admin.servicioAlCliente.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class NotificacionServiceImpl implements NotificacionService {

    @Autowired
    private NotificacionRepository notificacionRepository;

    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public List<NotificacionResponseDTO> verNotificaciones(Long numeroUsuario) {
        return notificacionRepository.buscarNotificacionDTO(numeroUsuario);
    }

    @Override
    public Notificacion abrirNotificacion(Long numeroNotificacion) {
        Optional<Notificacion> notificacion = notificacionRepository.findById(numeroNotificacion);
        return notificacion.orElse(null);

    }

    @Override
    public void crearNotificacion(Ticket ticket, String mensaje) {
        Notificacion notificacion = new Notificacion();
        notificacion.setTicket(ticket);
        notificacion.setMensaje(mensaje);
        notificacionRepository.save(notificacion);
    }

    @Override
    public void marcarComoLeida(Long numeroNotificacion){
        notificacionRepository.marcarLeidaNotificacion(numeroNotificacion);
    }
}
