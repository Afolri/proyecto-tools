package escom.admin.servicioAlCliente.entities;

import com.fasterxml.jackson.annotation.*;
import escom.admin.servicioAlCliente.dto.TicketResponseDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
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

    @ManyToOne
    @JoinColumn ( name = "numero_agente")
    @JsonProperty ( "agente")
    @JsonBackReference
    private Agente agente;

    @ManyToOne
    @JoinColumn (name = "numero_cliente")
    @JsonProperty("cliente")
    @JsonBackReference
    private Cliente cliente;

    @ManyToOne
    @JoinColumn (name = "numero_producto")
    @JsonProperty("producto")
    @JsonBackReference
    private ProductoTicket productoTicket;

    @OneToMany (mappedBy = "ticket", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Notificacion> notificaciones = new ArrayList<>();

    @OneToMany ( mappedBy = "ticket")
    @JsonManagedReference
    private List<ComentarioTicket> comentarioTicket;

}
