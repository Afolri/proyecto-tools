package escom.admin.servicioAlCliente.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuario_notificacion", schema = "soporte")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioNotificacion {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "numero_usuario_notificacion")
    @JsonProperty ("numero_usuario_notificacion")
    private Long numeroUsuarioNotificacion;

    @Column(name = "visto")
    private Boolean visto = false;

    @Column(name = "numero_usuario")
    @JsonProperty("numero_usuario")
    private Long numeroUsuario;

    @Column (name = "numero_notificacion")
    @JsonProperty("numero_notificacion")
    private Long numeroNotificacion;
}
