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

@NamedNativeQuery(
        name = "buscar_tickets_dto",
        query = """
                SELECT DISTINCT
                  t.numero_ticket,
                  pt.numero_compra_cot,
                  pt.numero_producto,
                  string_agg(ti.nombre_identificador::TEXT || ': ' || tipo.codigo::TEXT, ', ') AS tipo_codigo,
                  t.asunto,
                  t.numero_cliente,
                  c.nombre_cliente,
                  c.correo,
                  c.telefono,
                  t.descripcion,
                  t.estado,
                  t.numero_agente,
                  t.fecha,
                  t.hora
                FROM soporte.tickets t
                LEFT JOIN soporte.productoticket pt ON t.numero_producto = pt.numero_producto
                LEFT JOIN soporte.producto_tipo tipo ON pt.numero_producto = tipo.numero_producto
                LEFT JOIN soporte.tipo_identificador ti ON tipo.numero_identificador = ti.numero_identificador
                LEFT JOIN soporte.clientes c ON t.numero_cliente = c.numero_cliente
                LEFT JOIN soporte.agentes a ON t.numero_agente = a.numero_agente
                LEFT JOIN soporte.usuario u ON a.numero_usuario = u.numero_usuario
                WHERE u.numero_usuario = :numeroUsuario
                GROUP BY t.numero_ticket, pt.numero_compra_cot, pt.numero_producto, t.asunto,
                	   t.numero_cliente, c.nombre_cliente, c.correo, c.telefono, t.descripcion,
                	   t.estado, t.numero_agente, t.fecha
                ORDER BY t.numero_ticket DESC;
                """, resultSetMapping = "ticket_dto"
)
@SqlResultSetMapping(
        name = "ticket_dto",
        classes = @ConstructorResult(
                targetClass = TicketResponseDTO.class,
                columns = {
                        @ColumnResult(name = "numero_ticket", type = Long.class),
                        @ColumnResult(name = "numero_compra_cot", type = String.class),
                        @ColumnResult(name = "numero_producto", type = Long.class),
                        @ColumnResult(name = "tipo_codigo", type = String.class),
                        @ColumnResult(name = "asunto", type = String.class),
                        @ColumnResult(name = "numero_cliente", type = Long.class),
                        @ColumnResult(name = "nombre_cliente", type = String.class),
                        @ColumnResult(name = "correo", type = String.class),
                        @ColumnResult(name = "telefono", type = String.class),
                        @ColumnResult(name = "descripcion", type = String.class),
                        @ColumnResult(name = "estado", type = String.class),
                        @ColumnResult(name = "numero_agente", type = Long.class),
                        @ColumnResult(name = "fecha", type = LocalDate.class),
                        @ColumnResult(name = "hora", type = LocalTime.class)
                }
        )
)
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
