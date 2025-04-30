package escom.admin.servicioAlCliente.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import escom.admin.servicioAlCliente.dto.UsuarioResponseDTO;
import escom.admin.servicioAlCliente.entities.Rol;
import escom.admin.servicioAlCliente.entities.Usuario;
import escom.admin.servicioAlCliente.repositories.UsuarioRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        usuarioDTO.setNombreUsuario(usuario.getNombreUsuario());
        usuarioDTO.setRol(usuario.getRol());
        usuarioDTO.setNumeroUsuario(usuario.getNumeroUsuario());
        return usuarioDTO;
    }

    @Override
    public List<UsuarioResponseDTO> obtenerUsuarios() {
        try {
            List<Map<String,Object>> listaUsuarios = usuarioRepository.obtenerUsuarios();
            List<UsuarioResponseDTO> usuariosDTO = listaUsuarios.stream()
                    .map(obj -> UsuarioResponseDTO.builder()
                            .numeroUsuario((Long)obj.get("numero_usuario"))
                            .correo((String)obj.get("correo"))
                            .nombreUsuario((String) obj.get("nombre_usuario"))
                            .rol(Rol.obtenerRol((short)obj.get("rol")))
                            .build())
                    .toList();
            return usuariosDTO;
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }

    }
}
