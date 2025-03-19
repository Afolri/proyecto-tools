package escom.admin.servicioAlCliente.repositories;

import escom.admin.servicioAlCliente.entities.Agente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface AgentesRespository extends JpaRepository<Agente,Long> {

    @Query(value = """
            SELECT t.numero_ticket, t.numero_producto, pt.numero_serie_modelo, t.numero_producto_modelo,
            pt.numero_compra_cot, t.asunto,t.numero_cliente, c.nombre_cliente,
            c.telefono, t.descripcion, t.estado,t.numero_agente,  c.nombre_cliente
            FROM soporte.tickets t
            LEFT JOIN soporte.productoticket pt ON t.numero_producto_modelo = pt.numero_producto
            LEFT JOIN soporte.clientes c  ON  t.numero_cliente = c.numero_cliente ORDER BY numero_ticket DESC
            """, nativeQuery = true)
    List<Map<String,Object>> buscarTickets( @Param("numeroAgente") Long numeroAgente);
}
