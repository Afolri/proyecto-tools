package escom.admin.servicioAlCliente.entities;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;

@Entity
@Table(name = "producto_tipo", schema = "soporte")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "numero_producto_tipo")
public class ProductoTipo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "numero_producto_tipo")
    @JsonProperty("numero_producto_tipo")
    private Long numeroProductoTipo;

    @Column(name = "codigo")
    private String codigo;

    @ManyToOne
    @JoinColumn(name = "numero_producto")
    @JsonManagedReference
    private ProductoTicket productoTicket;

    @ManyToOne
    @JoinColumn(name = "numero_identificador")
    private TipoIdentificador tipoIdentificador;

    public Long getNumeroProductoTipo() {
        return numeroProductoTipo;
    }

    public void setNumeroProductoTipo(Long numeroProductoTipo) {
        this.numeroProductoTipo = numeroProductoTipo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public ProductoTicket getProductoTicket() {
        return productoTicket;
    }

    public void setProductoTicket(ProductoTicket productoTicket) {
        this.productoTicket = productoTicket;
    }

    public TipoIdentificador getTipoIdentificador() {
        return tipoIdentificador;
    }

    public void setTipoIdentificador(TipoIdentificador tipoIdentificador) {
        this.tipoIdentificador = tipoIdentificador;
    }
}
