package escom.admin.productos.repositories;

import escom.admin.productos.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    // Consulta para obtener información detallada de las computadoras, incluyendo características como el número de serie,
    // estado del producto, marca, procesador, RAM, fecha de configuración, usuario, versión y sistema operativo.
    // Esta consulta realiza uniones (INNER JOIN) entre las tablas "productos", "procesador" y "marcas".
    @Query (value= """
            SELECT
                id,
                noserie,
                estado_producto,
                marcas.nombre_marca AS id_marca,
                CONCAT(procesador.fabricante,' ',procesador.modelo) AS id_procesador,
                ram,
                fecha_configuracion,
                usuario,
                version_so,
                so
                FROM productos
                LEFT JOIN procesador
                ON productos.id_procesador = procesador.id_procesador
                LEFT JOIN marcas
                ON productos.id_marca = marcas.id_marca
            """, nativeQuery = true)
    List<Map<String,Object>> obtenerComputadoras ();

    // Consulta para obtener una lista de marcas de computadora con su respectivo "id_marca" y "nombre_marca".
    // Se extraen directamente los datos de la tabla "marcas".
    @Query (value= """
            SELECT id_marca,nombre_marca FROM marcas
            """, nativeQuery = true)
    List<Map<String,Object>> obtenerMarcasComputadora ();

}
