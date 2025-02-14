package escom.admin.servicioAlCliente.repositories;

import escom.admin.servicioAlCliente.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface TicketRepository extends JpaRepository<Ticket,Long>{
    List<Ticket> findAllByAgente_NumeroAgente(Long numeroAgente);
    Ticket findByClienteNombreCliente(String nombreCliente);
    @Query(value = """
            SELECT t.numero_ticket, t.asunto, t.descripcion, t.estado, t.cerrado, t.numero_agente,
            t.numero_cliente, t.numero_producto_modelo, pt.numero_serie_producto, pt.numero_compra_cot,
            c.numero_cliente, c.nombre_cliente, c.correo, c.telefono
            FROM tickets t
            LEFT JOIN productoticket pt ON t.numero_producto_modelo = pt.numero_producto_modelo
            LEFT JOIN clientes c  ON  t.numero_cliente = c.numero_cliente
            """, nativeQuery = true)
    List<Map<String,Object>> buscarTickets();
}
