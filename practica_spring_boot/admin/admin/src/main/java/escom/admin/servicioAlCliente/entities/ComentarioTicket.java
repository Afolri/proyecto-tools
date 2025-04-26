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

    @ManyToOne
    @JoinColumn ( name = "numero_comentario")
    @JsonProperty ( "numero_comentario")
    @JsonBackReference
    private Comentario comentario;

    @ManyToOne
    @JoinColumn ( name = "numero_ticket")
    @JsonProperty ( "numero_ticket")
    @JsonBackReference
    private Ticket ticket;

    @ManyToOne
    @JoinColumn ( name = "numero_agente")
    @JsonProperty ("numero_agente")
    @JsonBackReference
    private Agente agente;
}
