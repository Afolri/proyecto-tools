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
            SELECT sn.estado_notificacion as estado  FROM soporte.notificacion sn 
            WHERE sn.estado_notificacion = true LIMIT 1
            """, nativeQuery = true)
    void leerNotificacion ();

}
