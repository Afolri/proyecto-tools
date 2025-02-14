package escom.admin.servicioAlCliente.entities;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;

@Entity
@Table(name = "productoticket")
public class ProductoTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column ( name = "numero_producto_modelo")
    private Long numero_producto;

    @Column ( name = "numero_serie_producto" )
    private String numeroSerieProducto;

    @Column ( name = "numero_compra_cot")
    private String numeroCompraCot;


    public Long getNumero_producto() {
        return numero_producto;
    }

    public void setNumero_producto(Long numero_producto) {
        this.numero_producto = numero_producto;
    }

    public String getNumeroSerieProducto() {
        return numeroSerieProducto;
    }

    public void setNumeroSerieProducto(String numeroSerieProducto) {
        this.numeroSerieProducto = numeroSerieProducto;
    }


    public String getNumeroCompraCot() {
        return numeroCompraCot;
    }

    public void setNumeroCompraCot(String numeroCompraCot) {
        this.numeroCompraCot = numeroCompraCot;
    }
}
