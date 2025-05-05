package escom.admin.servicioAlCliente.Auth;

import escom.admin.servicioAlCliente.entities.Rol;
import escom.admin.servicioAlCliente.entities.Usuario;
import escom.admin.servicioAlCliente.jwt.JwtService;
import escom.admin.servicioAlCliente.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UsuarioRepository usuarioRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) {
        String correo = request.getCorreo().toLowerCase();
        System.out.println(correo);
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(correo,
                request.getPassword()));
        UserDetails user = usuarioRepository.findByCorreo(correo).orElseThrow();
        String token = jwtService.getToken(user);
        return AuthResponse.builder().token(token).build();
    }
    public AuthResponse register(RegisterRequest request) throws Exception {
        if(!request.password.equals(request.passwordConfirmar)){
            throw new Exception("Las contraseñas son diferentes");
        }
        Usuario usuario = Usuario.builder()
                .correo(request.getCorreo().toLowerCase())
                .nombreUsuario(request.getNombreUsuario())
                .password(passwordEncoder.encode(request.getPassword()))
                .rol(Rol.obtenerRol( Short.parseShort(request.getRol())))
                .build();
        usuarioRepository.save(usuario);
        return AuthResponse.builder()
                .token(jwtService.getToken(usuario))
                .build();
    }
}
