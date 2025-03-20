package escom.admin.servicioAlCliente.repositories;

    import escom.admin.servicioAlCliente.entities.ProductoTicket;
    import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.data.jpa.repository.Query;
    import org.springframework.stereotype.Repository;

    import javax.swing.text.html.Option;
    import java.util.List;
    import java.util.Map;
    import java.util.Optional;

    @Repository
    public interface ProductoTicketRepository extends JpaRepository<ProductoTicket, Long> {
        Optional<ProductoTicket> findByNumeroCompraCot(String numeroCompraCot);

    }