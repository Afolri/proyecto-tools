package escom.admin.productos.configuracion;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class Cors implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Permitir acceso CORS desde cualquier origen
        registry.addMapping("/**")
                .allowedOrigins("http://127.0.0.1:4200", "http://localhost:4200",
                        "http://192.168.2.40:4200")
                // Origen permitido
                .allowedMethods("GET", "POST", "PUT", "DELETE")  // Métodos permitidos
                .allowedHeaders("*")  // Permitir todos los encabezados
                .allowCredentials(true);  // Permitir credenciales si es necesario
    }
}
