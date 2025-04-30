package escom.admin.servicioAlCliente.repositories;

import escom.admin.servicioAlCliente.dto.UsuarioResponseDTO;
import escom.admin.servicioAlCliente.entities.Usuario;
import jakarta.transaction.Transactional;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByCorreo(String correo);

    @Transactional
    @Query(value = """
            SELECT numero_usuario, correo, nombre_usuario, rol FROM soporte.usuario
            """, nativeQuery = true)
    List<Map<String,Object>> obtenerUsuarios();

}
