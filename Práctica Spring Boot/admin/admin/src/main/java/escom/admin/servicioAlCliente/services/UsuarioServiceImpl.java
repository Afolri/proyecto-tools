package escom.admin.servicioAlCliente.services;

import escom.admin.servicioAlCliente.dto.UsuarioResponseDTO;
import escom.admin.servicioAlCliente.entities.Usuario;
import escom.admin.servicioAlCliente.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService{

    @Autowired
    UsuarioRepository usuarioRepository;
    @Override
    public UsuarioResponseDTO getUsuarioLoggeado(HttpHeaders headers) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = ((Usuario) authentication.getPrincipal()).getUsername();
        Usuario usuario = usuarioRepository.findByCorreo(email)
                .orElseGet(null);
        UsuarioResponseDTO usuarioDTO = new UsuarioResponseDTO();
        usuarioDTO.setCorreo(usuario.getCorreo());
        usuarioDTO.setNombre(usuario.getNombreUsuario());
        usuarioDTO.setRol(usuario.getRol());
        usuarioDTO.setNumeroUsuario(usuario.getNumeroUsuario());
        return usuarioDTO;
    }
}
