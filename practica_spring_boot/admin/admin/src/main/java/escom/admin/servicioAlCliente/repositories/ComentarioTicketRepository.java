package escom.admin.servicioAlCliente.repositories;

import escom.admin.servicioAlCliente.entities.ComentarioTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComentarioTicketRepository extends JpaRepository<ComentarioTicket, Long> {
    @Query(value = """
            SELECT * FROM soporte.comentario_ticket WHERE numero_ticket = :numeroTicket
            """, nativeQuery = true)
    List<ComentarioTicket> buscarPorNumeroTicket(@Param("numeroTicket") Long numeroTicket);
}
