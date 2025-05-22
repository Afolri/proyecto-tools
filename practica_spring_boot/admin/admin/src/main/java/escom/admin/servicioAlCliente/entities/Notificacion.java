package escom.admin.servicioAlCliente.entities;

import com.fasterxml.jackson.annotation.*;
import escom.admin.servicioAlCliente.dto.NotificacionResponseDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "notificaciones" , schema = "soporte")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "numero_notificacion")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notificacion {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "numero_notificacion")
    @JsonProperty("numero_notificacion")
    private Long numeroNotificacion;

    @Column (name = "mensaje")
    @JsonProperty("mensaje")
    private String mensaje;

    @Column(name = "fecha")
    private LocalDate fecha;

    @Column(name = "hora")
    @DateTimeFormat(pattern = "yyyy-MM-dd:HH:mm:ss")
    private LocalTime hora;

}
