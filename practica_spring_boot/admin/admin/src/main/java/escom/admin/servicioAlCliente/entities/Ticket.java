package escom.admin.servicioAlCliente.entities;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "tickets" , schema = "soporte")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "numero_ticket")
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "numero_ticket")
    @JsonProperty("numero_ticket")
    private Long numeroTicket;

    @Column (name = "asunto")
    private String asunto;

    @Column (name = "descripcion")
    private String descripcion;

    @Column (name = "estado")
    private String estado="NUEVO";

    @Column (name = "fecha")
    @DateTimeFormat(pattern = "yyyy-MM-dd:HH:mm:ss")
    private LocalDate fecha;

    @Column (name = "hora")
    private LocalTime hora;

    @Column ( name = "numero_agente")
    @JsonProperty ( "numero_agente")
    @JsonBackReference
    private Long numeroAgente;

    @Column (name = "numero_cliente")
    @JsonProperty("numero_cliente")
    private Long numeroCliente;

    @Column (name = "numero_producto")
    @JsonProperty("numero_producto")
    private Long numeroProducto;

    public Ticket(Long numeroTicket, String asunto, String descripcion, String estado, LocalDate fecha, LocalTime hora){
        this.numeroTicket = numeroTicket;
        this.asunto = asunto;
        this.descripcion = descripcion;
        this.estado = estado;
        this.fecha = fecha;
        this.hora = hora;
    }

}
