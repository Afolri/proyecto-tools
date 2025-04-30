package escom.admin.servicioAlCliente.services;

import escom.admin.servicioAlCliente.dto.UsuarioResponseDTO;
import escom.admin.servicioAlCliente.entities.Usuario;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UsuarioService {
    UsuarioResponseDTO getUsuarioLoggeado(HttpHeaders headers);
    List<UsuarioResponseDTO> obtenerUsuarios();
}
