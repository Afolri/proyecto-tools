package escom.admin.servicioAlCliente.services;

import escom.admin.servicioAlCliente.dto.NotificacionResponseDTO;
import escom.admin.servicioAlCliente.entities.Notificacion;
import escom.admin.servicioAlCliente.entities.Ticket;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface NotificacionService {
    List<NotificacionResponseDTO> verNotificaciones(Long numeroUsuario);
    void abrirNotificacion(Long numeroNotificacion);
    void crearNotificacion(Ticket ticket, String mensaje);
    public void marcarComoLeida(Long numeroNotificacion);
}
