package escom.admin.servicioAlCliente.entities;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "productoticket" , schema = "soporte")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "numero_producto")
public class ProductoTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column ( name = "numero_producto")
    @JsonProperty("numero_producto")
    private Long numeroProducto;

    @Column ( name = "numero_compra_cot")
    @JsonProperty("numero_compra_cot")
    private String numeroCompraCot;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "productoTicket", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<ProductoTipo> productoTipo = new ArrayList<>();;


    public Long getNumeroProducto() {
        return numeroProducto;
    }

    public void setNumeroProducto(Long numeroProducto) {
        this.numeroProducto = numeroProducto;
    }


    public String getNumeroCompraCot() {
        return numeroCompraCot;
    }

    public void setNumeroCompraCot(String numeroCompraCot) {
        this.numeroCompraCot = numeroCompraCot;
    }

    public List<ProductoTipo> getProductoTipo() {
        return productoTipo;
    }

    public void setProductoTipos(List<ProductoTipo> productoTipos) {
        this.productoTipo = productoTipos;
    }
}
