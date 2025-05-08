package escom.admin.servicioAlCliente.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import escom.admin.servicioAlCliente.dto.ComentarioTicketResponseDTO;
import escom.admin.servicioAlCliente.entities.*;
import escom.admin.servicioAlCliente.repositories.*;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class ComentarioTicketServiceImpl implements ComentarioTicketService{
    @Autowired
    ComentarioRepository comentarioRepository;
    @Autowired
    AgentesRespository agentesRespository;
    @Autowired
    TicketRepository ticketRepository;
    @Autowired
    ComentarioTicketRepository comentarioTicketRepository;
    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public ComentarioTicketResponseDTO comentarTicket(Long numeroTicket, Long numeroUsuario, String comentario) throws Exception {
        String comentarioTrim = comentario.trim();
        ComentarioTicket comentarioTicket = new ComentarioTicket();
        if (comentarioTrim != null && !comentarioTrim.isEmpty()&& numeroUsuario != null && numeroTicket != null) {
            Comentario comentarioGuardado = comentarioRepository.save(
                Comentario.builder()
                        .contenido(comentarioTrim)
                        .build());
            Usuario usuario = usuarioRepository.findById(numeroUsuario).orElseThrow();
            Ticket ticket = ticketRepository.findById(numeroTicket).orElseThrow();
            comentarioTicket = ComentarioTicket.builder()
                    .comentario(comentarioGuardado)
                    .ticket(ticket)
                    .usuario(usuario)
                    .build();
            comentarioTicketRepository.save(comentarioTicket);
            return ComentarioTicketResponseDTO.builder()
                    .comentario(comentarioTrim)
                    .numeroUsuario(numeroUsuario)
                    .numeroTicket(comentarioTicket.getTicket().getNumeroTicket())
                    .numeroComentario(comentarioTicket.getComentario().getNumeroComentario())
                    .build();
        }
        throw new Exception("Alguno de los campos esta vacio");
    }

    @Override
    public List<ComentarioTicketResponseDTO> buscarComentariosPorTicket(Long numeroTicket) {
        List<ComentarioTicket> comentariosEncontrados = comentarioTicketRepository.findAllByTicket_NumeroTicket(numeroTicket);
        return  comentariosEncontrados.stream()
                .map(comentario ->{
                    return ComentarioTicketResponseDTO.builder()
                            .numeroUsuario(comentario.getUsuario().getNumeroUsuario())
                            .numeroTicket(comentario.getTicket().getNumeroTicket())
                            .numeroComentario(comentario.getComentario().getNumeroComentario())
                            .comentario(comentario.getComentario().getContenido())
                            .build();

                })
                .toList();
    }
}
