package escom.admin.servicioAlCliente.repositories;

import escom.admin.servicioAlCliente.dto.NotificacionResponseDTO;
import escom.admin.servicioAlCliente.entities.UsuarioNotificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioNotificacionRepository extends JpaRepository<UsuarioNotificacion, Long> {
    @Query (value = """
            SELECT * FROM soporte.usuario_notificacion
            WHERE numero_notificacion = :numeroNotificacion
            AND numero_usuario = :numeroUsuario
            """, nativeQuery = true)
    Optional<UsuarioNotificacion> buscarNotificacionPorUsuario(@Param("numeroNotificacion") Long numeroNotificacion,
                                                              @Param("numeroUsuario")Long numeroUsuario);
}
