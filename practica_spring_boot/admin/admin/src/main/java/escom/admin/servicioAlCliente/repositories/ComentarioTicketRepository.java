package escom.admin.servicioAlCliente.repositories;

import escom.admin.servicioAlCliente.entities.ComentarioTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ComentarioTicketRepository extends JpaRepository<ComentarioTicket, Long> {
    List<ComentarioTicket> findAllByTicket_NumeroTicket(Long numeroTicket);
}
