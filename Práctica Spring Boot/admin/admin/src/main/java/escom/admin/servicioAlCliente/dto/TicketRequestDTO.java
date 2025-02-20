package escom.admin.servicioAlCliente.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TicketRequestDTO {
    @JsonProperty("numero_serie_modelo")
    private String numeroSerieModelo;
    @JsonProperty("nombre_cliente")
    private String nombreCliente;
    private String correo;
    private String telefono;
    private String asunto;
    @JsonProperty("numero_compra_cot")
    private String numeroCompraCot;
    private String descripcion;


    public String getNumeroSerieModelo() {
        return numeroSerieModelo;
    }

    public void setNumeroSerieModelo(String numeroSerieModelo) {
        this.numeroSerieModelo = numeroSerieModelo;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getNumeroCompraCot() {
        return numeroCompraCot;
    }

    public void setNumeroCompraCot(String numeroCompraCot) {
        this.numeroCompraCot = numeroCompraCot;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "TicketRequestDTO{" +
                "numeroDeSerie='" + numeroSerieModelo + '\'' +
                ", nombreCliente='" + nombreCliente + '\'' +
                ", correo='" + correo + '\'' +
                ", telefono='" + telefono + '\'' +
                ", asunto='" + asunto + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
