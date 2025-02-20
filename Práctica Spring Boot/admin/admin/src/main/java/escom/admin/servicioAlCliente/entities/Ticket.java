package escom.admin.servicioAlCliente.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import escom.admin.servicioAlCliente.repositories.ProductoTicketRepository;
import jakarta.persistence.*;

@Entity
@Table(name = "tickets")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "numero_ticket")
    @JsonProperty("numero_ticket")
    private Long numeroTicket;

    @Column (name = "asunto")
    private String asunto;

    @Column (name = "descripcion")
    private String descripcion;

    @Column (name = "estado")
    private String estado="NUEVO";

    @Column (name = "cerrado")
    private Boolean cerrado=false;

    @ManyToOne
    @JoinColumn ( name = "numero_agente")
    @JsonProperty ( "numero_agente")
    private Agente agente;

    @ManyToOne
    @JoinColumn (name = "numero_cliente")
    @JsonProperty("numero_cliente")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn (name = "numero_producto")
    @JsonProperty("numero_producto")
    private ProductoTicket productoTicket;

    public Long getNumeroTicket() {
        return numeroTicket;
    }

    public void setNumeroTicket(Long numeroTicket) {
        this.numeroTicket = numeroTicket;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }


    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Boolean getCerrado() {
        return cerrado;
    }

    public void setCerrado(Boolean cerrado) {
        this.cerrado = cerrado;
    }

    public Agente getAgente() {
        return agente;
    }

    public void setAgente(Agente agente) {
        this.agente = agente;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public ProductoTicket getProductoTicket() {
        return productoTicket;
    }

    public void setProductoTicket(ProductoTicket productoTicket) {
        this.productoTicket = productoTicket;
    }
}
