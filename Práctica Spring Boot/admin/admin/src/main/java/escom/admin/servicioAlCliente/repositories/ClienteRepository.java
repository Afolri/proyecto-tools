package escom.admin.servicioAlCliente.repositories;

import escom.admin.servicioAlCliente.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Long> {
    Optional<Cliente> findByCorreo(String correo);
}
