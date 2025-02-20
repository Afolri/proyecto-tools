package escom.admin.servicioAlCliente.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name = "clientes")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "numero_cliente")
    @JsonProperty("numero_cliente")
    Long numeroCliente;

    @Column(name = "nombre_cliente")
    @JsonProperty("nombre_cliente")
    String nombreCliente;

    @Column (name = "correo")
    String correo;

    @Column (name = "telefono")
    String telefono;

    public Long getNumeroCliente() {
        return numeroCliente;
    }

    public void setNumeroCliente(Long numeroCliente) {
        this.numeroCliente = numeroCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
