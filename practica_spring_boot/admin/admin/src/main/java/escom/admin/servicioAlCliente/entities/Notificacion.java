package escom.admin.servicioAlCliente.entities;

import com.fasterxml.jackson.annotation.*;
import escom.admin.servicioAlCliente.dto.NotificacionResponseDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


@NamedNativeQuery(
        name="buscar_notificacion_dto",
        query = """
        SELECT sn.numero_notificacion, st.numero_ticket, st.fecha,st.hora,sn.estado_notificacion, sn.mensaje,st.estado, sc.nombre_cliente,
		a.numero_usuario FROM soporte.notificacion sn
        LEFT JOIN soporte.tickets st ON st.numero_ticket = sn.numero_ticket
        LEFT JOIN soporte.clientes sc ON sc.numero_cliente = st.numero_cliente
        LEFT JOIN soporte.agentes a ON st.numero_agente = a.numero_agente
        LEFT JOIN soporte.usuario u ON a.numero_usuario = u.numero_usuario
        WHERE u.numero_usuario = :numeroUsuario
        ORDER BY numero_notificacion DESC
            """, resultSetMapping = "notificacion_dto"
)
@SqlResultSetMapping(
        name = "notificacion_dto",
        classes = @ConstructorResult(
                targetClass = NotificacionResponseDTO.class,
                columns = {
                        @ColumnResult(name = "numero_notificacion", type = Long.class),
                        @ColumnResult(name = "numero_ticket", type = Long.class),
                        @ColumnResult(name = "numero_usuario", type = Long.class),
                        @ColumnResult(name = "fecha", type = LocalDate.class),
                        @ColumnResult(name = "hora", type = LocalTime.class),
                        @ColumnResult(name = "estado_notificacion", type = Boolean.class),
                        @ColumnResult(name = "mensaje", type = String.class),
                        @ColumnResult(name = "estado", type=String.class),
                        @ColumnResult(name = "nombre_cliente", type = String.class)
                }
        )
)
@Entity
@Table(name = "notificacion" , schema = "soporte")
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

    @Column (name = "estado_notificacion")
    @JsonProperty ("estado_notificacion")
    private Boolean estadoNotificacion = false;

    @Column (name = "mensaje")
    @JsonProperty("mensaje")
    private String mensaje;

    @ManyToOne
    @JoinColumn (name = "numero_ticket", nullable = false)
    @JsonBackReference
    private Ticket ticket;

}
