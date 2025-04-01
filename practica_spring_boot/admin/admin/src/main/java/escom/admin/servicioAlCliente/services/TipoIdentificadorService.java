package escom.admin.servicioAlCliente.services;

import escom.admin.servicioAlCliente.dto.TipoIdentificadorResponseDTO;
import escom.admin.servicioAlCliente.entities.TipoIdentificador;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TipoIdentificadorService {
    List<TipoIdentificadorResponseDTO> obtenerIdentificadores();
    void agregarIdentificador(String nombreIdentificador);
}
