package escom.admin.servicioAlCliente.entities;

import com.fasterxml.jackson.annotation.*;
import escom.admin.servicioAlCliente.dto.TipoIdentificadorResponseDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@NamedNativeQuery(
        name = "buscar_identificadores",
        query = """
                SELECT numero_identificador, nombre_identificador
                FROM soporte.tipo_identificador
                """,resultSetMapping = "identificador_dto"
)
@SqlResultSetMapping(
        name = "identificador_dto",
        classes = @ConstructorResult(
                targetClass = TipoIdentificadorResponseDTO.class,
                columns = {
                        @ColumnResult(name = "numero_identificador", type = Long.class),
                        @ColumnResult(name = "nombre_identificador", type = String.class)
                }
        )
)
@Table(name = "tipo_identificador", schema = "soporte")
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Data
@NoArgsConstructor
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

}
