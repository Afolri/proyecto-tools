package escom.admin.servicioAlCliente.repositories;

    import escom.admin.servicioAlCliente.entities.ProductoTicket;
    import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.data.jpa.repository.Query;
    import org.springframework.data.repository.query.Param;
    import org.springframework.stereotype.Repository;

    import javax.swing.text.html.Option;
    import java.util.List;
    import java.util.Map;
    import java.util.Optional;

    @Repository
    public interface ProductoTicketRepository extends JpaRepository<ProductoTicket, Long> {
        Optional<ProductoTicket> findByNumeroCompraCot(String numeroCompraCot);
        @Query(value = """
            SELECT string_agg(ti.nombre_identificador::TEXT || ': ' || tipo.codigo::TEXT, ', ') AS tipo_codigo\s
            FROM soporte.producto_tipo pt
            LEFT JOIN soporte.producto_tipo tipo ON pt.numero_producto = tipo.numero_producto
            LEFT JOIN soporte.tipo_identificador ti ON tipo.numero_identificador = ti.numero_identificador
            WHERE pt.numero_producto = :numeroProducto
            """,nativeQuery = true)
        String obtenerIdentificadores (@Param("numeroProducto")Long numeroProducto);
    }