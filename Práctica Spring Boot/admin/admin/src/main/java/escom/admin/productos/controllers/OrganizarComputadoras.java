package escom.admin.productos.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import escom.admin.productos.services.ProcesadorService;
import escom.admin.productos.services.ProductoService;
import escom.admin.productos.services.ProductoServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping ("/inicio")
public class OrganizarComputadoras {
    private final ProductoService productoService;
    private final ProcesadorService procesadorService;

    public OrganizarComputadoras(ProductoServiceImpl productoServiceImpl, ProcesadorService procesadorService) {
        this.productoService = productoServiceImpl;
        this.procesadorService = procesadorService;
    }

    /**
     * Accede a la base de datos y obtiene los datos de la tabla productos
     * @return retorna una lisat de las filas obtenidas en un JSON
     */
    @GetMapping("/obtenerproductos")
    public ResponseEntity<List<Map<String,Object>>> obtenerProductos(){
        return ResponseEntity.ok(productoService.obtenerProductos());
    }

    /**
     * Guarda los productos
     * @param producto es un JSON con los atributos de un producto el cual se guarda
     * @throws JsonProcessingException en caso de no obtener los datos
     */
    @PostMapping("/guardarproducto")
    public ResponseEntity<?> guardarProducto(@RequestBody String producto) throws JsonProcessingException {
        return (productoService.guardarProductos(producto))? ResponseEntity.ok().build():
                ResponseEntity.badRequest().build();
    }

    /**
     * obtiene las marcas que existen en la base de datos
     * @return obtiene en un JSON el identificador y el nombre de la marca obtenida
     */
    @GetMapping("/obtenerMarcasComputadoras")
    public ResponseEntity<?> obtenerMarcas(){
        return ResponseEntity.ok(productoService.obtenerMarcasComputadoras());
    }
    @GetMapping("/obtenerProcesadores")
    public ResponseEntity<?> obtenerProcesadores(){
        return ResponseEntity.ok(procesadorService.obtenerProcesadores());
    }

}

