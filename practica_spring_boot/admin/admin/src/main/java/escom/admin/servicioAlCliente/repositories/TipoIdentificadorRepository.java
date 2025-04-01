package escom.admin.servicioAlCliente.repositories;

import escom.admin.servicioAlCliente.dto.TipoIdentificadorResponseDTO;
import escom.admin.servicioAlCliente.entities.TipoIdentificador;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TipoIdentificadorRepository extends JpaRepository<TipoIdentificador, Long> {
    Optional<TipoIdentificador> findByNumeroIdentificador(Long numeroIdentificador);

    @Transactional
    @Query(name = "buscar_identificadores", nativeQuery = true)
    List<TipoIdentificadorResponseDTO> buscarIdentificadores();
}
