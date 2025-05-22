package escom.admin.servicioAlCliente.repositories;

import escom.admin.servicioAlCliente.dto.TicketResponseDTO;
import escom.admin.servicioAlCliente.entities.ProductoTipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductoTipoRepository extends JpaRepository<ProductoTipo, Long> {
    @Query(value = """
        SELECT * FROM soporte.producto_tipo st
        WHERE st.numero_producto = :numeroProducto AND st.numero_identificador = :numeroIdentificador
        """, nativeQuery = true)
    Optional<ProductoTipo> buscarProductoIdentificador(
        @Param("numeroProducto") Long numeroProducto,
        @Param("numeroIdentificador") Long numeroIdentificador);



}
