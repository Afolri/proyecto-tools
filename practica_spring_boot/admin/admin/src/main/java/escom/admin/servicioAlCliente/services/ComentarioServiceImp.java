package escom.admin.servicioAlCliente.services;

import escom.admin.servicioAlCliente.entities.Agente;
import escom.admin.servicioAlCliente.entities.Comentario;
import escom.admin.servicioAlCliente.entities.Ticket;
import escom.admin.servicioAlCliente.repositories.AgentesRespository;
import escom.admin.servicioAlCliente.repositories.ComentarioRepository;
import escom.admin.servicioAlCliente.repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComentarioServiceImp implements ComentarioService {
    @Autowired
    ComentarioRepository comentarioRepository;
    @Autowired
    AgentesRespository agentesRespository;
    @Autowired
    TicketRepository ticketRepository;


    @Override
    public void comentar(Long numeroAgente, Long numeroTicket, String contenido) {
        Agente agente = agentesRespository.findById(numeroAgente).orElseThrow();
        Ticket ticket = ticketRepository.findById(numeroTicket).orElseThrow();
        Comentario comen = Comentario.builder()
                .agente(agente)
                .ticket(ticket)
                .contenido(contenido).build();
        comentarioRepository.save(comen);
    }

    @Override
    public List<Comentario> verComentarios(Long numeroAgente, Long numeroTicket) {
        return List.of();
    }
}
