package escom.admin.productos.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler (CustomException.class)
    public ResponseEntity<Map<String, String>> handleCustomException(CustomException e){
        Map<String, String> respuesta = new HashMap<>();
        respuesta.put("error", e.getMessage());
        return ResponseEntity.badRequest().body(respuesta);
    }
}
