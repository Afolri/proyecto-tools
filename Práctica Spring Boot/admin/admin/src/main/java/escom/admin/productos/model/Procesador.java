package escom.admin.productos.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "procesador")
public class Procesador {

    @Column (name = "id_procesador")
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    Integer idProcesador;

    @Column ( name = "fabricante")
    String fabricante;

    @Column (name = "modela")
    String modelo;

    public Integer getIdProcesador() {
        return idProcesador;
    }

    public void setIdProcesador(Integer idProcesador) {
        this.idProcesador = idProcesador;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
}
