package escom.admin.servicioAlCliente.repositories;

import escom.admin.servicioAlCliente.dto.NotificacionResponseDTO;
import escom.admin.servicioAlCliente.entities.Notificacion;
import jakarta.transaction.Transactional;
import org.hibernate.annotations.Parent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface NotificacionRepository extends JpaRepository<Notificacion, Long> {
    List<Notificacion> findAllByTicket_Agente_NumeroAgente(Long numeroAgente);

    @Query(name = "buscar_notificacion_dto", nativeQuery = true)
    List<NotificacionResponseDTO> buscarNotificacionDTO(@Param("numeroUsuario") Long numeroUsuario);

    @Query(value = """
            UPDATE soporte.notificacion
            	SET estado_notificacion=false
            	WHERE numero_notificacion = :numeroNotificacion;
            """, nativeQuery = true)
    void marcarLeidaNotificacion(@Param("numeroNotificacion") Long numeroNotificacion);

    @Transactional
    @Query(value = """
            SELECT * FROM (SELECT CASE WHEN sn.estado_notificacion IS false THEN true ELSE false END AS estado  FROM soporte.notificacion sn)
            ORDER BY estado DESC
            LIMIT 1
            """, nativeQuery = true)
    boolean notificacionesSinVer ();
    @Query(value = """
                    SELECT sn.numero_notificacion, st.numero_ticket, st.fecha,st.hora,sn.estado_notificacion, sn.mensaje,st.estado, sc.nombre_cliente,
            		a.numero_usuario FROM soporte.notificacion sn
                    LEFT JOIN soporte.tickets st ON st.numero_ticket = sn.numero_ticket
                    LEFT JOIN soporte.clientes sc ON sc.numero_cliente = st.numero_cliente
                    LEFT JOIN soporte.agentes a ON st.numero_agente = a.numero_agente
                    LEFT JOIN soporte.usuario u ON a.numero_usuario = u.numero_usuario
                    ORDER BY numero_notificacion DESC
            """, nativeQuery = true)
    List<Map<String,Object>> buscarTodasLasNotificaciones();

}
