package escom.admin.productos.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "productos")
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Producto {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column ( name = "id")
    private Long id;

    @Column (name = "noserie")
    private String noserie;

    @Column (name = "estado_producto")
    private String estado_producto;

    @JsonProperty("id_marca")
    @Column (name = "id_marca")
    private Integer idMarca;

    @JsonProperty("id_procesador")
    @Column (name = "id_procesador")
    private Integer idProcesador;

    @Column (name = "ram")
    private String ram;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @Column (name = "fecha_configuracion")
    private LocalDate fecha_configuracion;

    @Column (name = "usuario")
    private String usuario;

    @Column (name = "so")
    private Byte so;

    @Column (name = "version_so")
    private Integer version_so;

}
