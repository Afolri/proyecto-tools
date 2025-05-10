package escom.admin.servicioAlCliente.repositories;

import escom.admin.servicioAlCliente.dto.TicketResponseDTO;
import escom.admin.servicioAlCliente.entities.Agente;
import escom.admin.servicioAlCliente.entities.Ticket;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket,Long>{
    List<Ticket> findAllByAgente_NumeroAgente(Long numeroAgente);
    Ticket findByClienteNombreCliente(String nombreCliente);
    Optional<Ticket> findByCliente_CorreoAndProductoTicket_NumeroCompraCot(String correo, String numeroCompraCot);

    @Transactional
    @Query(value = """
       SELECT t.numero_ticket,t.numero_producto,  pt.numero_compra_cot, rept.codigo, ti.nombre_identificador,
       t.asunto,t.numero_cliente, c.nombre_cliente, c.correo,
       c.telefono, t.descripcion, t.estado, t.numero_agente,  c.nombre_cliente, t.fecha, t.hora
       FROM soporte.tickets t
       LEFT JOIN soporte.productoticket pt ON t.numero_producto = pt.numero_producto
       LEFT JOIN soporte.producto_tipo rept ON pt.numero_producto = rept.numero_producto
       LEFT JOIN soporte.tipo_identificador ti ON ti.numero_identificador = rept.numero_identificador
       LEFT JOIN soporte.clientes c  ON  t.numero_cliente = c.numero_cliente
       WHERE UPPER (ti.nombre_identificador) LIKE UPPER (:nombreIdentificador) AND t.numero_ticket = :numeroTicket
       ORDER BY numero_ticket DESC
       """,nativeQuery = true)
    Optional<Ticket> buscarPorIdentificador(@Param("nombreIdentificador") String nombreIdentificador
       , @Param("numeroTicket") Long numeroTicket);

   @Transactional
   @Modifying
   @Query(value = """
    UPDATE soporte.tickets
        SET estado = :estado
        WHERE soporte.tickets.numero_ticket = :numeroTicket;
    """,nativeQuery = true)
    void cambiarEstado( @Param("numeroTicket") Long numeroTicket, @Param ("estado") String estado);

    @Transactional
    @Query(value = """
            SELECT DISTINCT
                t.numero_ticket,
                pt.numero_compra_cot,
                pt.numero_producto,
                string_agg(ti.nombre_identificador::TEXT || ': ' || tipo.codigo::TEXT, ', ') AS tipo_codigo,
                t.asunto,
                t.numero_cliente,
                c.nombre_cliente,
                c.correo,
                c.telefono,
                t.descripcion,
                t.estado,
                t.numero_agente,
                t.fecha,
                t.hora
              FROM soporte.tickets t
              LEFT JOIN soporte.productoticket pt ON t.numero_producto = pt.numero_producto
              LEFT JOIN soporte.producto_tipo tipo ON pt.numero_producto = tipo.numero_producto
              LEFT JOIN soporte.tipo_identificador ti ON tipo.numero_identificador = ti.numero_identificador
              LEFT JOIN soporte.clientes c ON t.numero_cliente = c.numero_cliente
              LEFT JOIN soporte.agentes a ON t.numero_agente = a.numero_agente
              LEFT JOIN soporte.usuario u ON a.numero_usuario = u.numero_usuario
              WHERE u.numero_usuario = 10 AND t.estado LIKE CONCAT('%',:estadoticket,'%')
              GROUP BY t.numero_ticket, pt.numero_compra_cot, pt.numero_producto, t.asunto,
                   t.numero_cliente, c.nombre_cliente, c.correo, c.telefono, t.descripcion,
                   t.estado, t.numero_agente, t.fecha
              ORDER BY t.numero_ticket DESC;
            """, nativeQuery = true)
    List<Map<String, Object>> buscarTicketsDTO(@Param("numeroUsuario") Long numeroUsuario,
                                               @Param("estadoticket")String estadoTicket);

    @Transactional
    @Query(value = """
            SELECT numero_agente FROM (
                SELECT numero_agente, min(ultima_asignacion), c FROM(
                   SELECT DISTINCT numero_agente, nombres_agente, max(TO_TIMESTAMP(CONCAT(fecha,' ',hora), 'YYYY-MM-DD HH24:MI')) AS ultima_asignacion, COUNT(*) AS c FROM(
                       SELECT sa.numero_agente, sa.nombres_agente, CASE WHEN st.fecha ISNULL THEN '1999/12/21' ELSE st.fecha END AS fecha,
                       CASE WHEN st.hora ISNULL THEN CURRENT_TIME ELSE st.hora END AS hora FROM soporte.agentes sa
                       LEFT JOIN soporte.tickets st ON sa.numero_agente = st.numero_agente
                       GROUP BY sa.numero_agente, sa.nombres_agente, st.numero_ticket
                       )
                   GROUP BY numero_agente, nombres_agente
                )
                GROUP BY numero_agente, ultima_asignacion,c
                ORDER BY ultima_asignacion ASC
                    )
                WHERE c = (SELECT GREATEST(1, min(cuenta)) FROM (
                    SELECT sa.numero_agente, COUNT(st.numero_ticket) AS cuenta\s
                    FROM soporte.agentes sa
                    LEFT JOIN soporte.tickets st ON sa.numero_agente = st.numero_agente
                    GROUP BY sa.numero_agente
                   ) subquery
                )
                
                LIMIT 1
            """, nativeQuery = true)
    Long siguienteAgente();

    @Query(value = """
            SELECT DISTINCT
            	  t.numero_ticket,
            	  pt.numero_compra_cot,
            	  pt.numero_producto,
            	  string_agg(ti.nombre_identificador::TEXT || ': ' || tipo.codigo::TEXT, ', ') AS tipo_codigo,
            	  t.asunto,
            	  t.numero_cliente,
            	  c.nombre_cliente,
            	  c.correo,
            	  c.telefono,
            	  t.descripcion,
            	  t.estado,
            	  t.numero_agente,
            	  t.fecha,
            	  t.hora
            	FROM soporte.tickets t
            	LEFT JOIN soporte.productoticket pt ON t.numero_producto = pt.numero_producto
            	LEFT JOIN soporte.producto_tipo tipo ON pt.numero_producto = tipo.numero_producto
            	LEFT JOIN soporte.tipo_identificador ti ON tipo.numero_identificador = ti.numero_identificador
            	LEFT JOIN soporte.clientes c ON t.numero_cliente = c.numero_cliente
            	LEFT JOIN soporte.agentes a ON t.numero_agente = a.numero_agente
            	LEFT JOIN soporte.usuario u ON a.numero_usuario = u.numero_usuario
            	GROUP BY t.numero_ticket, pt.numero_compra_cot, pt.numero_producto, t.asunto,
            		   t.numero_cliente, c.nombre_cliente, c.correo, c.telefono, t.descripcion,
            		   t.estado, t.numero_agente, t.fecha
            	ORDER BY t.numero_ticket DESC;
            """, nativeQuery = true)
    List<Map<String,Object>> obtenerTodosLosTickets();

}
