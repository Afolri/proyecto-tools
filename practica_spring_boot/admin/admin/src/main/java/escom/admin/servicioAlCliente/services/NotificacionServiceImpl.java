package escom.admin.servicioAlCliente.services;

import escom.admin.servicioAlCliente.dto.NotificacionResponseDTO;
import escom.admin.servicioAlCliente.entities.Notificacion;
import escom.admin.servicioAlCliente.entities.Ticket;
import escom.admin.servicioAlCliente.repositories.NotificacionRepository;
import escom.admin.servicioAlCliente.repositories.TicketRepository;
import escom.admin.servicioAlCliente.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        try{
            return notificacionRepository.buscarNotificacionDTO(numeroUsuario);
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public void abrirNotificacion(Long numeroNotificacion) {
        Notificacion notificacion = notificacionRepository.findById(numeroNotificacion).orElse(null);
        assert notificacion != null;
        notificacion.setEstadoNotificacion(true);
        Ticket ticket = notificacion.getTicket();
        ticket.setEstado("ABIERTO");
        notificacion.setTicket(ticket);
        notificacionRepository.save(notificacion);

    }

    @Override
    public NotificacionResponseDTO crearNotificacion(Ticket ticket, String mensaje) {
        Notificacion notificacion = new Notificacion();
        notificacion.setTicket(ticket);
        notificacion.setMensaje(mensaje);
        Notificacion notiTemp = notificacionRepository.save(notificacion);
        return NotificacionResponseDTO.builder()
                .numeroNotificacion(notiTemp.getNumeroNotificacion())
                .numeroTicket(notiTemp.getTicket().getNumeroTicket())
                .usuarioAgenteAsignado(ticket.getAgente().getUsuario().getNumeroUsuario())
                .fecha(ticket.getFecha())
                .hora(ticket.getHora())
                .estadoNotificacion(notificacion.getEstadoNotificacion())
                .mensaje(notificacion.getMensaje())
                .estado(ticket.getEstado())
                .nombreCliente(ticket.getCliente().getNombreCliente())
                .build();
    }

    @Override
    public void marcarComoLeida(Long numeroNotificacion){
        notificacionRepository.marcarLeidaNotificacion(numeroNotificacion);
    }

    @Override
    public boolean notificacionesPendientes() {
        return notificacionRepository.notificacionesSinVer();
    }
}
