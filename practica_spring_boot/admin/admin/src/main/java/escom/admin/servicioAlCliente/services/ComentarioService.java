package escom.admin.servicioAlCliente.services;

import escom.admin.servicioAlCliente.entities.Comentario;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ComentarioService {
    void comentar(Long numeroAgente, Long numeroTicket, String contenido);
    List<Comentario> verComentarios(Long numeroAgente, Long numeroTicket);
}
