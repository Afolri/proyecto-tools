package escom.admin.servicioAlCliente.config;

import escom.admin.servicioAlCliente.jwt.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.hibernate.engine.spi.SessionEventListenerManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtAuthenticationFilter authenticationFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain( HttpSecurity http) throws Exception {

        return http
                .cors(Customizer.withDefaults())
                .csrf(csrf ->
                        csrf.disable()
                )
                .authorizeHttpRequests(authRequest ->
                        authRequest
                                .requestMatchers("/auth/**", "/reporte-tickets/crear-ticket",
                                        "/reporte-tickets/obtener-identificadores").permitAll()
                                .anyRequest().authenticated()
                        )
                .sessionManagement(sessionManager ->
                        sessionManager
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
    @Bean
    CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration configuracion = new CorsConfiguration();
        configuracion.setAllowedOrigins(List.of("http://localhost:4200", "http://127.0.0.1:4200",
                "http://192.168.2.40:4200","http://angular_app:8083"));
        configuracion.setAllowedMethods(List.of("GET","POST","PUT","DELETE"));
        configuracion.setAllowedHeaders(List.of("Authorization","Cache-Control","Content-Type"));
        configuracion.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",configuracion);
        return source;
    }
}
