package escom.admin.productos.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "procesador")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Procesador {

    @Column (name = "id_procesador")
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    Long idProcesador;

    @Column ( name = "fabricante")
    String fabricante;

    @Column (name = "modela")
    String modelo;

}
