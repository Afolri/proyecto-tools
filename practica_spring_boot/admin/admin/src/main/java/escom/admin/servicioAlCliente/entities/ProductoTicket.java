package escom.admin.servicioAlCliente.entities;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "productoticket" , schema = "soporte")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "numero_producto")
@Data
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

}
