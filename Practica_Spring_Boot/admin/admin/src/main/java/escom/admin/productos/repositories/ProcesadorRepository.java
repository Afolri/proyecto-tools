package escom.admin.productos.repositories;

import escom.admin.productos.model.Procesador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface ProcesadorRepository extends JpaRepository<Procesador, Integer> {

    @Query(value = """
            SELECT COUNT(modelo) > 0 AS nomproce FROM procesador
            WHERE UPPER(CONCAT(fabricante,' ',modelo)) LIKE UPPER (:nombreprocesador)
            """,nativeQuery = true)
    boolean existeProcesador(@Param("nombreprocesador") String nombreprocesador);

    boolean existsByIdProcesador(Integer idProcesador);

    @Query (value = """
            SELECT id_procesador,CONCAT(fabricante,' ',modelo)AS procesador
            FROM procesador
            """, nativeQuery = true)
    List<Map<String,Object>> obtenerProcesadores();
}
