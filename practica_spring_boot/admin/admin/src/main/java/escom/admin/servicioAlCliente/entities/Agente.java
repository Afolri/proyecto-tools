package escom.admin.servicioAlCliente.entities;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "agentes" , schema = "soporte")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "numero_agente")
@Data
public class Agente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "numero_agente")
    @JsonProperty("numero_agente")
    private Long numeroAgente;

    @Column(name = "nombres_agente")
    @JsonProperty("nombres_agente")
    private String nombreAgente;

    @Column(name = "apellidos_agente")
    @JsonProperty("apellidos_agente")
    private String apellidosAgente;

    @OneToOne
    @JoinColumn(name = "numero_usuario")
    @JsonProperty("numero_usuario")
    private Usuario numeroUsuario;

}
