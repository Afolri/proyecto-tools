package escom.admin.productos.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface ProductoService {
     List<Map<String,Object>> obtenerProductos();
     List<Map<String,Object>> obtenerMarcasComputadoras();
     boolean guardarProductos(String producto) throws JsonProcessingException;
}
