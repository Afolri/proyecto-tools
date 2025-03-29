package escom.admin.productos.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table ( name = "marcas")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Marca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_marca")
    Long idMarca;

    @Column (name = "nombre_marca")
    String nombreMarca;
}
