package escom.admin.servicioAlCliente.entities;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "agentes" , schema = "soporte")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "numero_agente")
public class Agente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "numero_agente")
    @JsonProperty("numero_agente")
    Long numeroAgente;

    @Column(name = "nombres_agente")
    @JsonProperty("nombres_agente")
    String nombreAgente;

    @Column(name = "apellidos_agente")
    @JsonProperty("apellidos_agente")
    String apellidosAgente;

    @OneToMany (mappedBy = "agente")
    @JsonManagedReference
    private List<Comentario> comentarios = new ArrayList<>();


    public Long getNumeroAgente() {
        return numeroAgente;
    }

    public void setNumeroAgente(Long numeroAgente) {
        this.numeroAgente = numeroAgente;
    }

    public String getNombreAgente() {
        return nombreAgente;
    }

    public void setNombreAgente(String nombreAgente) {
        this.nombreAgente = nombreAgente;
    }

    public String getApellidosAgente() {
        return apellidosAgente;
    }

    public void setApellidosAgente(String apellidosAgente) {
        this.apellidosAgente = apellidosAgente;
    }
}
