package escom.admin.servicioAlCliente.dto;

public class TicketRequestDTO {
    private String numeroDeSerie;
    private String nombreCliente;
    private String correo;
    private String telefono;
    private String asunto;
    private String numeroCompraCot;
    private String descripcion;


    public String getNumeroDeSerie() {
        return numeroDeSerie;
    }

    public void setNumeroDeSerie(String numeroDeSerie) {
        this.numeroDeSerie = numeroDeSerie;
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
                "numeroDeSerie='" + numeroDeSerie + '\'' +
                ", nombreCliente='" + nombreCliente + '\'' +
                ", correo='" + correo + '\'' +
                ", telefono='" + telefono + '\'' +
                ", asunto='" + asunto + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
