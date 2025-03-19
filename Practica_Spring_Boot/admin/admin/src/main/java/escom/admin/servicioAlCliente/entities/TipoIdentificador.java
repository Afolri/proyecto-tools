package escom.admin.servicioAlCliente.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Table(name = "tipo_identificador", schema = "soporte")
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "numero_identificador")
public class TipoIdentificador {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @JsonProperty("numero_identificador")
    @Column(name = "numero_identificador")
    private Long numeroIdentificador;

    @Column(name = "nombre_identificador")
    @JsonProperty("nombre_identificador")
    private String nombreIdentificador;

    @OneToMany(mappedBy = "tipoIdentificador", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductoTipo> productoTipo = new ArrayList<>();


    public TipoIdentificador(){
    }
    public TipoIdentificador(String nombreIdentificador){
        this.nombreIdentificador = nombreIdentificador;
    }
    public Long getNumeroIdentificador() {
        return numeroIdentificador;
    }

    public void setNumeroIdentificador(Long numeroIdentificador) {
        this.numeroIdentificador = numeroIdentificador;
    }

    public String getNombreIdentificador() {
        return nombreIdentificador;
    }

    public void setNombreIdentificador(String nombreIdentificador) {
        this.nombreIdentificador = nombreIdentificador;
    }

    public List<ProductoTipo> getProductoTipo() {
        return productoTipo;
    }

    public void setProductoTipo(List<ProductoTipo> productoTipo) {
        this.productoTipo = productoTipo;
    }
}
