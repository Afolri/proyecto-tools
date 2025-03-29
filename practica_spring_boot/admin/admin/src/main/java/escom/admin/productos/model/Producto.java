package escom.admin.productos.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "productos")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Producto {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column ( name = "id")
    private Integer id;

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


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNoserie() {
        return noserie;
    }

    public void setNoserie(String noserie) {
        this.noserie = noserie;
    }

    public String getEstado_producto() {
        return estado_producto;
    }

    public void setEstado_producto(String estado_producto) {
        this.estado_producto = estado_producto;
    }

    public Integer getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(Integer idMarca) {
        this.idMarca = idMarca;
    }

    public Integer getIdProcesador() {
        return idProcesador;
    }

    public void setIdProcesador(Integer idProcesador) {
        this.idProcesador = idProcesador;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public LocalDate getFecha_configuracion() {
        return fecha_configuracion;
    }

    public void setFecha_configuracion(LocalDate fecha_configuracion) {
        this.fecha_configuracion = fecha_configuracion;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Byte getSo() {
        return so;
    }

    public void setSo(Byte so) {
        this.so = so;
    }

    public Integer getVersion_so() {
        return version_so;
    }

    public void setVersion_so(Integer version_so) {
        this.version_so = version_so;
    }
}
