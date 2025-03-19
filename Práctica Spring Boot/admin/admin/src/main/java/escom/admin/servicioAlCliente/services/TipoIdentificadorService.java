package escom.admin.servicioAlCliente.services;

import org.springframework.stereotype.Service;

@Service
public interface TipoIdentificadorService {
    void agregarIdentificador(String nombreIdentificador);
}
