package escom.admin.servicioAlCliente.services;

import escom.admin.servicioAlCliente.dto.ComentarioTicketResponseDTO;
import escom.admin.servicioAlCliente.entities.*;
import escom.admin.servicioAlCliente.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
            comentarioTicket = ComentarioTicket.builder()
                    .numeroComentario(comentarioGuardado.getNumeroComentario())
                    .numeroTicket(numeroTicket)
                    .numeroUsuario(numeroUsuario)
                    .build();
            comentarioTicketRepository.save(comentarioTicket);
            return ComentarioTicketResponseDTO.builder()
                    .comentario(comentarioTrim)
                    .numeroUsuario(numeroUsuario)
                    .numeroTicket(comentarioTicket.getNumeroTicket())
                    .numeroComentario(comentarioTicket.getNumeroComentario())
                    .build();
        }
        throw new Exception("Alguno de los campos esta vacio");
    }

    @Override
    public List<ComentarioTicketResponseDTO> buscarComentariosPorTicket(Long numeroTicket) {
        List<ComentarioTicket> comentariosEncontrados = comentarioTicketRepository.buscarPorNumeroTicket(numeroTicket);
        List<Long> numeroComentario = comentariosEncontrados.stream()
                .map(ComentarioTicket::getNumeroComentario)
                .toList();
        List<Comentario> comentarios = comentarioRepository.findAllById(numeroComentario);
        return  comentariosEncontrados.stream()
                .map(comentario ->{
                    return ComentarioTicketResponseDTO.builder()
                            .numeroUsuario(comentario.getNumeroUsuario())
                            .numeroTicket(comentario.getNumeroTicket())
                            .numeroComentario(comentario.getNumeroComentario())
                            .numeroComentario(comentarios.stream()
                                    .filter(comentarioLocal -> comentarioLocal.getNumeroComentario().equals(comentario.getNumeroComentario()) )
                                    .findFirst()
                                    .map(Comentario::getNumeroComentario)
                                    .orElseThrow()
                            )
                            .comentario(comentarios.stream()
                                    .filter(comentarioLocal -> comentarioLocal.getNumeroComentario().equals(comentario.getNumeroComentario()) )
                                    .findFirst()
                                    .map(Comentario::getContenido)
                                    .orElseThrow())
                            .build();

                })
                .toList();
    }
}
