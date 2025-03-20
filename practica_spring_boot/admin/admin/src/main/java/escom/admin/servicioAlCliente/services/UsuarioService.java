package escom.admin.servicioAlCliente.services;

import escom.admin.servicioAlCliente.dto.UsuarioResponseDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

@Service
public interface UsuarioService {
    UsuarioResponseDTO getUsuarioLoggeado(HttpHeaders headers);
}
