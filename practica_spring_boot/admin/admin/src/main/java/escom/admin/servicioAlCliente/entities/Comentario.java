package escom.admin.servicioAlCliente.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table (name = "comentarios", schema = "soporte")
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class Comentario {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "numero_comentario")
    @JsonProperty ("numero_comentario")
    private Long numeroComentario;

    @Column (name = "contenido")
    private String contenido;

    @Column (name = "numero_agente")
    @JsonProperty("numero_Agente")
    @JoinColumn (name = "numero_agente")
    private Agente agente;

    @Column (name = "numero_ticket")
    @JsonProperty("numero_ticket")
    @JoinColumn (name = "numero_ticket")
    private Ticket ticket;
}
