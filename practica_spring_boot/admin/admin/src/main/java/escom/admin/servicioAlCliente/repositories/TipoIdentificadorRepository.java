package escom.admin.servicioAlCliente.repositories;

import escom.admin.servicioAlCliente.entities.TipoIdentificador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TipoIdentificadorRepository extends JpaRepository<TipoIdentificador, Long> {
    Optional<TipoIdentificador> findByNombreIdentificador(String nombreIdentificador);
}
