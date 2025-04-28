package escom.admin.servicioAlCliente.entities;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
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
    @JsonBackReference
    private ProductoTicket productoTicket;

    @ManyToOne
    @JoinColumn(name = "numero_identificador")
    @JsonBackReference
    private TipoIdentificador tipoIdentificador;


}
