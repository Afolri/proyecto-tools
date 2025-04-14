package escom.admin.servicioAlCliente.config;

import escom.admin.servicioAlCliente.jwt.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.security.Principal;


@Component
public class WebSocketAuthInterceptor implements ChannelInterceptor {

    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserDetailsService userDetailsService;// Tu servicio para validar el token

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
        System.out.println("⚡ Interceptor ejecutado: " + accessor.getCommand());
//
//        // Si es una conexión (CONNECT)
//        if (StompCommand.CONNECT.equals(accessor.getCommand())) {
//            String tokenHeader = accessor.getFirstNativeHeader("Authorization");
//
//            // Si la ruta es pública (/topic) no verificamos el token
//            if (accessor.getDestination() != null && accessor.getDestination().startsWith("/topic") && tokenHeader == null) {
//                System.out.println("Ruta pública - No se requiere token");
//                return message;
//            }
//
//            // Si se encuentra el token y es válido, configuramos el contexto de seguridad
//            if (tokenHeader != null && tokenHeader.startsWith("Bearer ")) {
//                String token = tokenHeader.substring(7);
//                String correo = jwtService.getCorreoFromToken(token);
//                UserDetails userDetails = userDetailsService.loadUserByUsername(correo);
//                try {
//                    if (correo != null && jwtService.isTokenValid(token, userDetails)) {
//                        Authentication auth = new UsernamePasswordAuthenticationToken(correo, null, null); // o con authorities si tienes
//                        SecurityContextHolder.getContext().setAuthentication(auth);
//                        accessor.setUser(auth);
//                    } else {
//                        throw new IllegalArgumentException("Token inválido");
//                    }
//                } catch (Exception e) {
//                    throw new IllegalArgumentException("Token no válido: " + e.getMessage());
//                }
//            } else {
//                throw new IllegalArgumentException("Token no presente");
//            }
//        }
//
//        // Si es una suscripción (SUBSCRIBE)
//        if (StompCommand.SUBSCRIBE.equals(accessor.getCommand())) {
//            String destination = accessor.getDestination();
//            Principal user = accessor.getUser();
//
//            // Protegemos las rutas privadas (/topic/{numero_usuario})
//            if (destination != null && destination.matches("^/topic/\\d+$") && user == null) {
//                System.out.println("🚫 Suscripción rechazada por falta de autenticación");
//
//                // Construir mensaje de error STOMP
//                StompHeaderAccessor errorAccessor = StompHeaderAccessor.create(StompCommand.ERROR);
//                errorAccessor.setMessage("No autorizado para suscribirse a: " + destination);
//                errorAccessor.setLeaveMutable(true);
//
//                return MessageBuilder.createMessage(new byte[0], errorAccessor.getMessageHeaders());
//            }
//        }

        return message;
    }



}
