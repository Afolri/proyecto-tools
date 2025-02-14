package escom.admin.productos.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import escom.admin.productos.model.Producto;
import escom.admin.productos.repositories.ProcesadorRepository;
import escom.admin.productos.repositories.ProductoRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import java.util.Map;

@Service  // Asegúrate de que la clase esté anotada con @Service
public class ProductoServiceImpl implements ProductoService{

    private final ProductoRepository productoRepository;
    private final ProcesadorRepository procesadorRepository;

    @Autowired  // Inyección por constructor (es preferible a la inyección por campo)

    public ProductoServiceImpl(ProductoRepository productoRepository, ProcesadorRepository procesadorRepository) {
        this.productoRepository = productoRepository;
        this.procesadorRepository = procesadorRepository;
    }


    // Método para obtener todos los productos
    @Override
    public List<Map<String,Object>> obtenerProductos() {
        return productoRepository.obtenerComputadoras();
    }
    public List<Map<String,Object>> obtenerMarcasComputadoras(){
        return productoRepository.obtenerMarcasComputadora();
    }

    @Override
    public boolean guardarProductos(String producto) throws JsonProcessingException {
            ObjectMapper miobjeto = new ObjectMapper();
            miobjeto.registerModule(new JavaTimeModule());
            JSONObject mijson = new JSONObject(producto);
            Producto productoresultado = miobjeto.readValue(mijson.toString(), Producto.class);
            productoRepository.save(productoresultado);
            return true;
    }
}

