package escom.admin.servicioAlCliente.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import escom.admin.servicioAlCliente.dto.NotificacionResponseDTO;
import escom.admin.servicioAlCliente.entities.*;
import escom.admin.servicioAlCliente.repositories.NotificacionRepository;
import escom.admin.servicioAlCliente.repositories.TicketRepository;
import escom.admin.servicioAlCliente.repositories.UsuarioNotificacionRepository;
import escom.admin.servicioAlCliente.repositories.UsuarioRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class NotificacionServiceImpl implements NotificacionService {

    @Autowired
    private NotificacionRepository notificacionRepository;

    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioNotificacionRepository usuarioNotificacionRepository;

    @Override
    public List<NotificacionResponseDTO> verNotificaciones(Long numeroUsuario) {
        try{
            Usuario usuario = usuarioRepository.findById(numeroUsuario).orElseThrow();
            ObjectMapper objectMapper = new ObjectMapper();
            return notificacionRepository.buscarNotificacionesDTO(numeroUsuario).stream()
                    .map(obj ->{
                        objectMapper.registerModule(new JavaTimeModule());
                        try {
                            return objectMapper.readValue(new JSONObject(obj).toString(), NotificacionResponseDTO.class);
                        } catch (JsonProcessingException e) {
                            throw new RuntimeException(e);
                        }
                    }).toList();
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public void abrirNotificacion(Long numeroNotificacion, Long numeroUsuario) {
        UsuarioNotificacion notificacion =
                usuarioNotificacionRepository.buscarNotificacionPorUsuario(numeroNotificacion, numeroUsuario).orElseThrow();
        notificacion.setVisto(true);
        usuarioNotificacionRepository.save(notificacion);

    }

    @Override
    public NotificacionResponseDTO crearNotificacion(Long numeroUsuario, String mensaje) {
        Notificacion notificacion = new Notificacion();
        notificacion.setMensaje(mensaje);
        notificacion.setFecha(LocalDate.now());
        notificacion.setHora(LocalTime.parse(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"))));
        Notificacion notiTemp = notificacionRepository.save(notificacion);
        List<Long> numeroUsuarios = usuarioRepository.obtenerTodosLosAdmin();
        numeroUsuarios.add(numeroUsuario);

        List<UsuarioNotificacion> usuarioNotificaciones = numeroUsuarios.stream()
                        .map(numUsuario ->{
                            UsuarioNotificacion temp = new UsuarioNotificacion();
                            temp.setNumeroUsuario(numUsuario);
                            temp.setNumeroNotificacion(notiTemp.getNumeroNotificacion());
                            return temp;
                        }).toList();
        usuarioNotificacionRepository.saveAll(usuarioNotificaciones);

        return NotificacionResponseDTO.builder()
                .numeroUsuario(numeroUsuario)
                .fecha(LocalDate.now())
                .hora(LocalTime.now())
                .visto(false)
                .mensaje(mensaje)
                .build();
    }

    @Override
    public void marcarComoLeida(Long numeroNotificacion){
        notificacionRepository.marcarLeidaNotificacion(numeroNotificacion);
    }

    @Override
    public boolean notificacionesPendientes(Long numeroUsuario) {
        return notificacionRepository.notificacionesSinVer(numeroUsuario);
    }
}
