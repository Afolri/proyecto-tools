package escom.admin.servicioAlCliente.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "comentario_ticket", schema = "soporte")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties (ignoreUnknown = true)
@Builder
public class ComentarioTicket {

    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY)
    @Column (name = "numero_comentario_ticket")
    @JsonProperty ("numero_comentario_ticket")
    private Long numerocomentarioTicket;

    @Column ( name = "numero_comentario")
    @JsonProperty ( "numero_comentario")
    private Long numeroComentario;

    @JsonProperty ("numero_ticket")
    @Column ( name = "numero_ticket")
    private Long numeroTicket;

    @Column ( name = "numero_usuario")
    @JsonProperty ("numero_usuario")
    private Long numeroUsuario;
}
