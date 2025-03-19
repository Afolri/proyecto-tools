package escom.admin.servicioAlCliente.repositories;

import escom.admin.servicioAlCliente.entities.TipoIdentificador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TipoIdentificadorRepository extends JpaRepository<TipoIdentificador, Long> {
    Optional<TipoIdentificador> findByNombreIdentificador(String nombreIdentificador);
}
