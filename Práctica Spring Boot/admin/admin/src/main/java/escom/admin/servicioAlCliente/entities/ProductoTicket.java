package escom.admin.servicioAlCliente.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name = "productoticket")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductoTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column ( name = "numero_producto")
    @JsonProperty("numero_producto")
    private Long numeroProducto;

    @Column ( name = "numero_serie_modelo" )
    @JsonProperty("numero_serie_modelo")
    private String numeroSerieModelo;

    @Column ( name = "numero_compra_cot")
    @JsonProperty("numero_compra_cot")
    private String numeroCompraCot;


    public Long getNumeroProducto() {
        return numeroProducto;
    }

    public void setNumeroProducto(Long numeroProducto) {
        this.numeroProducto = numeroProducto;
    }

    public String getNumeroSerieModelo() {
        return numeroSerieModelo;
    }

    public void setNumeroSerieModelo(String numeroSerieModelo) {
        this.numeroSerieModelo = numeroSerieModelo;
    }


    public String getNumeroCompraCot() {
        return numeroCompraCot;
    }

    public void setNumeroCompraCot(String numeroCompraCot) {
        this.numeroCompraCot = numeroCompraCot;
    }
}
