package escom.admin.servicioAlCliente.services;

import escom.admin.servicioAlCliente.dto.ComentarioTicketResponseDTO;
import escom.admin.servicioAlCliente.entities.Agente;
import escom.admin.servicioAlCliente.entities.Comentario;
import escom.admin.servicioAlCliente.entities.ComentarioTicket;
import escom.admin.servicioAlCliente.entities.Ticket;
import escom.admin.servicioAlCliente.repositories.AgentesRespository;
import escom.admin.servicioAlCliente.repositories.ComentarioRepository;
import escom.admin.servicioAlCliente.repositories.ComentarioTicketRepository;
import escom.admin.servicioAlCliente.repositories.TicketRepository;
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

    @Override
    public ComentarioTicketResponseDTO comentarTicket(Long numeroTicket, Long numeroUsuario, String comentario) throws Exception {
        String comentarioTrim = comentario.trim();
        ComentarioTicket comentarioTicket = new ComentarioTicket();
        if (comentarioTrim != null && !comentarioTrim.isEmpty()&& numeroUsuario != null && numeroTicket != null) {
            Comentario comentarioGuardado = comentarioRepository.save(
                Comentario.builder()
                        .contenido(comentarioTrim)
                        .build());
            Agente agente = agentesRespository.findByUsuario_NumeroUsuario(numeroUsuario).orElseThrow();
            System.out.println(agente.getNumeroAgente());
            Ticket ticket = ticketRepository.findById(numeroTicket).orElseThrow();
            comentarioTicket = ComentarioTicket.builder()
                    .comentario(comentarioGuardado)
                    .ticket(ticket)
                    .agente(agente)
                    .build();
            comentarioTicketRepository.save(comentarioTicket);
            return ComentarioTicketResponseDTO.builder()
                    .comentario(comentarioTrim)
                    .numeroAgente(comentarioTicket.getAgente().getNumeroAgente())
                    .numeroTicket(comentarioTicket.getTicket().getNumeroTicket())
                    .numeroComentario(comentarioTicket.getComentario().getNumeroComentario())
                    .build();
        }
        throw new Exception("Alguno de los campos esta vacio");
    }

    @Override
    public List<Comentario> buscarComentariosPorTicket(Long numeroTicket) {
        List<ComentarioTicket> comentariosEncontrados = comentarioTicketRepository.findAllByTicket_NumeroTicket(numeroTicket);
        List<Comentario> comentarios = new ArrayList<>();
        comentarios = comentariosEncontrados.stream()
                .map(ComentarioTicket::getComentario)
                .sorted(Comparator.comparing(Comentario::getNumeroComentario))
                .toList();
        return comentarios;
    }
}
