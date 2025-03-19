package escom.admin.productos.services;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface ProcesadorService {
    List<Map<String,Object>> obtenerProcesadores();
}
