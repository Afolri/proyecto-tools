package escom.admin.servicioAlCliente.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TicketRequestDTO {
    @JsonProperty("codigo")
    private String codigo;
    @JsonProperty("nombre_identificador")
    private String nombreIdentificador;
    @JsonProperty("nombre_cliente")
    private String nombreCliente;
    private String correo;
    private String telefono;
    private String asunto;
    @JsonProperty("numero_compra_cot")
    private String numeroCompraCot;
    private String descripcion;


    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombreIdentificador() {
        return nombreIdentificador;
    }

    public void setNombreIdentificador(String nombreIdentificador) {
        this.nombreIdentificador = nombreIdentificador;
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

}
