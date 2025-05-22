package escom.admin.servicioAlCliente.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table (name = "comentarios", schema = "soporte")
@Builder
public class Comentario {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @JsonProperty ("numero_comentario")
    @Column (name = "numero_comentario")
    private Long numeroComentario;

    @Column(name = "contenido")
    private String contenido;

}
