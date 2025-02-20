package escom.admin.servicioAlCliente.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;

@Entity
@Table(name = "agentes")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Agente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "numero_agente")
    @JsonProperty("numero_agente")
    Long numeroAgente;

    @Column(name = "nombre_agente")
    @JsonProperty("nombre_agente")
    String nombreAgente;

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
}
