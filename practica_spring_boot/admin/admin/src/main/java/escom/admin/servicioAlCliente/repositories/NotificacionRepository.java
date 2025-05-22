package escom.admin.servicioAlCliente.repositories;

import escom.admin.servicioAlCliente.dto.NotificacionResponseDTO;
import escom.admin.servicioAlCliente.entities.Notificacion;
import jakarta.transaction.Transactional;
import org.hibernate.annotations.Parent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Repository
public interface NotificacionRepository extends JpaRepository<Notificacion, Long> {

    @Query(value = """
        SELECT sn.numero_notificacion, su.numero_usuario, sn.fecha, sn.hora,sun.visto, sn.mensaje FROM soporte.usuario_notificacion sun
        LEFT JOIN soporte.usuario su ON sun.numero_usuario = su.numero_usuario
        LEFT JOIN soporte.notificaciones sn ON sun.numero_notificacion = sn.numero_notificacion
        WHERE su.numero_usuario = :numeroUsuario
        ORDER BY sn.numero_notificacion DESC
        """, nativeQuery = true)
    List<Map<String,Object>> buscarNotificacionesDTO(@RequestParam ("numeroUsuario") Long numeroUsuario);

    @Query(value = """
        UPDATE soporte.notificacion
            SET visto=true
            WHERE numero_notificacion = :numeroNotificacion;
            """, nativeQuery = true)
    void marcarLeidaNotificacion(@Param("numeroNotificacion") Long numeroNotificacion);

    @Transactional
    @Query(value = """
                SELECT CASE WHEN COUNT(*) > 0   THEN true ELSE false	END AS estado	FROM
                (SELECT CASE WHEN sun.visto IS false THEN true ELSE false END AS estado, sun.numero_usuario  FROM soporte.usuario_notificacion sun)
                WHERE numero_usuario = :numeroUsuario AND estado = false
                ORDER BY estado DESC
                LIMIT 1
            """, nativeQuery = true)
    boolean notificacionesSinVer (@Param("numeroUsuario") Long numeroUsuario);

    @Query(value = """
        SELECT sn.numero_notificacion, su.numero_usuario, sn.fecha, sn.hora, sn.visto, sn.mensaje FROM soporte.usuario_notificacion sun
        LEFT JOIN soporte.usuario su ON sun.numero_usuario = su.numero_usuario
        LEFT JOIN soporte.notificaciones sn ON sun.numero_notificacion = sn.numero_notificacion
        ORDER BY sn.numero_notificacion DESC
            """, nativeQuery = true)
    List<Map<String,Object>> buscarTodasLasNotificaciones();

}
